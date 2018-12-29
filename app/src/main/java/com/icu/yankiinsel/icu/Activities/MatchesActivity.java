package com.icu.yankiinsel.icu.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.icu.yankiinsel.icu.Interfaces.ListItemClickListener;
import com.icu.yankiinsel.icu.Adapters.MatchesRecyclerAdapter;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.*;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity implements ListItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<User> userSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        mRecyclerView = (RecyclerView) findViewById(R.id.matches_recycler_view);

        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        userSet = new ArrayList<User>();

        for (int i = 0; i < Utils.getLikedUsers().size(); i++) {
            userSet.add(Utils.getLikedUsers().get(i));
        }

        mAdapter = new MatchesRecyclerAdapter(userSet, this);
        mRecyclerView.setAdapter(mAdapter);
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
                Intent k = new Intent(MatchesActivity.this, ProfileActivity.class);
                startActivity(k);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.action_preferences) {
            try {
                Intent k = new Intent(MatchesActivity.this, PreferencesActivity.class);
                startActivity(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (id == R.id.action_home) {
            try {
                Intent k = new Intent(MatchesActivity.this, HomeActivity.class);
                startActivity(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        try {
            Intent k = new Intent(MatchesActivity.this, MessageListActivity.class);
            k.putExtra("USER_INDEX", clickedItemIndex);
            startActivity(k);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
