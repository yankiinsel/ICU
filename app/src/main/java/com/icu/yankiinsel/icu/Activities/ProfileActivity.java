package com.icu.yankiinsel.icu.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;

public class ProfileActivity extends AppCompatActivity {


    private EditText mUsernameTextView;
    private ImageView mImageView;
    private EditText mLocationTextView;
    private TextView mInterestsTextView;
    private Button mBtnAddInterest;
    private EditText mEditInterestView;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mUsernameTextView = (EditText)  findViewById(R.id.tv_profile_name);
        mImageView = (ImageView) findViewById(R.id.profile_imageView);
        mLocationTextView = (EditText)  findViewById(R.id.tv_profile_location);
        mInterestsTextView = (TextView)  findViewById(R.id.tv_profile_interests);
        mBtnAddInterest = (Button) findViewById(R.id.btn_add_interest);
        mEditInterestView = (EditText) findViewById(R.id.edit_interest);
        mInterestsTextView.setMovementMethod(new ScrollingMovementMethod());

        prepareViews();
    }

    private void prepareViews() {
        mUser = Utils.getExampleUsers().get(0);

        mUsernameTextView.setText(mUser.getNameAge());
        mImageView.setImageResource(getResources().getIdentifier(mUser.imageName, "drawable", getPackageName()));
        mLocationTextView.setText(String.valueOf(mUser.location));

        reloadInterests();

        mBtnAddInterest.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String interest = mEditInterestView.getText().toString();
                if (interest.length() > 0) {
                    mUser.interests.add(interest);
                    reloadInterests();
                }
                mEditInterestView.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            try {
                Intent k = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.action_preferences) {
            try {
                Intent k = new Intent(ProfileActivity.this, PreferencesActivity.class);
                startActivity(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.action_matches)
        {
            try {
                Intent k = new Intent(ProfileActivity.this, MatchesActivity.class);
                startActivity(k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void reloadInterests() {
        mInterestsTextView.setText("");
        for (String interest: mUser.interests) {
            mInterestsTextView.setText(interest + "\n" + mInterestsTextView.getText());
        }
    }
}
