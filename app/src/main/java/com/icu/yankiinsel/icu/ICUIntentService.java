package com.icu.yankiinsel.icu;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.icu.yankiinsel.icu.Activities.HomeActivity;
import com.icu.yankiinsel.icu.Adapters.HomeRecyclerAdapter;
import com.icu.yankiinsel.icu.Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


public class ICUIntentService extends IntentService {

    private ContentResolver mResolver;
    HomeRecyclerAdapter mAdapter;
    SharedPreferences mPreferences;
    Context mContext;
    public static String userJsonStr = null;

    public ICUIntentService() {
        super("ICUIntentService");
        mContext = getBaseContext();
        mAdapter = (HomeRecyclerAdapter)HomeActivity.mAdapter;

        mResolver = HomeActivity.contentResolver;
    }
    public long addUser(String name, String surname, int age, int genderId, String location, String image_string){

        Cursor userCursor;
        Long userId;

        userCursor = mResolver.query(UserContract.UserEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        if (userCursor.moveToFirst()){
            int locationIdIndex = userCursor.getColumnIndex(UserContract.UserEntry._ID);
            userId = userCursor.getLong(locationIdIndex);
        }
        else {
            Uri insertedUri;
            ContentValues userValues = new ContentValues();
            userValues.put(UserContract.UserEntry.COLUMN_NAME, name);
            userValues.put(UserContract.UserEntry.COLUMN_SURNAME, surname);
            userValues.put(UserContract.UserEntry.COLUMN_AGE, age);
            userValues.put(UserContract.UserEntry.COLUMN_GENDER_ID, genderId);
            userValues.put(UserContract.UserEntry.COLUMN_LOCATION, location);
            userValues.put(UserContract.UserEntry.COLUMN_IMAGE, image_string);
            insertedUri = mResolver.insert(UserContract.UserEntry.CONTENT_URI, userValues);
            userId = ContentUris.parseId(insertedUri);
        }
        return userId;
    }

    @Override
    protected void onHandleIntent( Intent intent) {

        HttpURLConnection urlConnection   = null;
        BufferedReader reader          = null;

        String genderPref= intent.getExtras().getString("gender");
        String locationPref = intent.getExtras().getString("location");
        String minAgePref = intent.getExtras().getString("minAge");
        String maxAgePref = intent.getExtras().getString("maxAge");

        final String BASE_URL = "https://ICU-api.now.sh/user?";
        final String genderParam = "gender";
        final String locationParam = "location";
        final String minAgeParam = "minAge";
        final String maxAgeParam = "maxAge";

        String uriString;
        StringBuilder extra = new StringBuilder();
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(minAgeParam , minAgePref)
                .appendQueryParameter(maxAgeParam , maxAgePref)
                .build();

        if(!locationPref.equals("All")) {
            extra.append("&" + locationParam + "=" + locationPref);
        }
        if(!genderPref.equals("All")){
            extra.append("&" + genderParam + "=" + genderPref);
        }

        uriString = builtUri.toString() + extra;
        Log.v("HomeActivity", uriString);

        try{
            URL userURL = new URL(uriString);
            urlConnection  = (HttpURLConnection) userURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer     = new StringBuffer();

            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() != 0) {
                    userJsonStr = buffer.toString();
                }
            }

        }catch (IOException ex){
            Log.e("HomeActivity", "Error ", ex);
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException ex) {
                    Log.e("HomeActivity", "Error closing stream", ex);
                }
            }
        }

        Gson gson = new Gson();
        User[] users = gson.fromJson(userJsonStr, User[].class);;

        Log.v("HomeActivity", userJsonStr);

        for(int i = 0; i < users.length; i++){
            int genderInput;
            if(users[i].gender.equals("Male"))
            {
                genderInput = 1;
            }else if (users[i].gender.equals("Female"))
            {
                genderInput = 2;
            }else if (users[i].gender.equals("Attack Helicopter"))
            {
                genderInput = 3;
            }else{
                genderInput = 4;
            }
            long userId = addUser(users[i].name, users[i].surname, users[i].age,genderInput,users[i].location, users[i].imageName);
        }

        mAdapter.myDataset.clear();
        mAdapter.myDataset = Arrays.asList(users);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(mAdapter);
        return;
    }

}
