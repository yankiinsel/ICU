package com.icu.yankiinsel.icu.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.icu.yankiinsel.icu.Adapters.HomeRecyclerAdapter;
import com.icu.yankiinsel.icu.Model.Gender;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<User> dataset = new ArrayList<>();

        User user1 = Utils.getExampleUsers().get(1);
        User user2 = Utils.getExampleUsers().get(2);

        Collections.addAll(user1.interests, "Movies", "Video Games", "Music");
        Collections.addAll(user2.interests, "Travel", "Books", "Food");

        dataset.add(user1);
        dataset.add(user2);

        mAdapter = new HomeRecyclerAdapter(dataset);
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
}