package com.icu.yankiinsel.icu.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.icu.yankiinsel.icu.Model.Gender;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;

import java.util.Collections;

public class ProfileActivity extends AppCompatActivity {


    private EditText mUsernameTextView;
    private ImageView mImageView;
    private EditText mLocationTextView;
    private EditText mInterestsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mUsernameTextView = (EditText)  findViewById(R.id.tv_profile_name);
        mImageView = (ImageView) findViewById(R.id.profile_imageView);
        mLocationTextView = (EditText)  findViewById(R.id.tv_profile_location);
        mInterestsTextView = (EditText)  findViewById(R.id.tv_profile_interests);
        mInterestsTextView.setMovementMethod(new ScrollingMovementMethod());

        prepareViews();
    }

    private void prepareViews() {
        User user = new User("Mahmut", "Kutal", 35, Gender.MALE, "Sariyer");
        user.imageName = "man1";
        Collections.addAll(user.interests, "Movies", "Video Games", "Music");

        mUsernameTextView.setText(user.getNameAge());
        mImageView.setImageResource(getResources().getIdentifier(user.imageName, "drawable", getPackageName()));
        mLocationTextView.setText(String.valueOf(user.location));

        for (String interest: user.interests) {
            mInterestsTextView.setText(interest + "\n"+ mInterestsTextView.getText());
        }
    }
}
