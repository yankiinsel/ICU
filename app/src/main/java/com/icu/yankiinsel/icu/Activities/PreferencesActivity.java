package com.icu.yankiinsel.icu.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.icu.yankiinsel.icu.SettingsFragment;

public class PreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}