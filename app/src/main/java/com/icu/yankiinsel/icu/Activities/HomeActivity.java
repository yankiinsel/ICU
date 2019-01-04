package com.icu.yankiinsel.icu.Activities;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Build;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.icu.yankiinsel.icu.Adapters.HomeRecyclerAdapter;
import com.icu.yankiinsel.icu.ICUIntentService;
import com.icu.yankiinsel.icu.Model.Gender;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.NotificationUtils;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.ReminderUtilities;
import com.icu.yankiinsel.icu.UserContract;
import com.icu.yankiinsel.icu.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ChargingBroadcastReceiver mChargingReceiver;
    IntentFilter mChargingIntentFilter;
    public static ContentResolver contentResolver;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReminderUtilities.scheduleMessageReminder(getApplicationContext());
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mChargingIntentFilter = new IntentFilter();
        mChargingIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        mChargingIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        mChargingReceiver = new ChargingBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        contentResolver = getContentResolver();
        Cursor cur = getContentResolver().query(UserContract.UserEntry.CONTENT_URI,
                null,
                null,
                null,
                null);


        mAdapter = new HomeRecyclerAdapter(cur);

//        FetchUserInfo task = new FetchUserInfo((HomeRecyclerAdapter)mAdapter, preferences, this);
//        task.execute();

        updateUsers();

        mRecyclerView.setAdapter(mAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            showCharging(batteryManager.isCharging());
        } else {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent currentBatteryStatusIntent = registerReceiver(null, ifilter);
            int batteryStatus = currentBatteryStatusIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = false;
            if (batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING || batteryStatus == BatteryManager.BATTERY_STATUS_FULL){
                isCharging = true;
            }
            showCharging(isCharging);
        }

        registerReceiver(mChargingReceiver, mChargingIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mChargingReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {
            try {
                Intent k = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(k);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.action_preferences) {
            try {
                Intent k = new Intent(HomeActivity.this, PreferencesActivity.class);
                startActivity(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.action_matches)
        {
            try {
                Intent k = new Intent(HomeActivity.this, MatchesActivity.class);
                startActivity(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCharging(boolean isCharging){
        if (isCharging) {
            Toast toast = Toast.makeText(this, getResources().getString(R.string.phone_charging),
                    Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, getResources().getString(R.string.phone_unplugged),
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private class ChargingBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean isCharging = (action.equals(Intent.ACTION_POWER_CONNECTED));
            showCharging(isCharging);
        }
    }

    private void updateUsers() {

        String genderPref= preferences.getString("gender", "");
        String locationPref = preferences.getString("location","");
        String minAgePref = preferences.getString("minAge","");
        String maxAgePref = preferences.getString("maxAge","");
        Intent intent = new Intent(this, ICUIntentService.class);
        intent.putExtra("gender", genderPref);
        intent.putExtra("location", locationPref);
        intent.putExtra("minAge", minAgePref);
        intent.putExtra("maxAge", maxAgePref);
        this.startService(intent);
    }

}
