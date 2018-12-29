package com.icu.yankiinsel.icu.Tasks;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.icu.yankiinsel.icu.Adapters.HomeRecyclerAdapter;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.UserContract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class FetchUserInfo extends AsyncTask<String, Void, String> {

    HomeRecyclerAdapter mAdapter;
    SharedPreferences mPreferences;
    ContentResolver mResolver;
    Context mContext;

    public FetchUserInfo(HomeRecyclerAdapter adapter, SharedPreferences preferences, Context context) {
        mAdapter   = adapter;
        mPreferences = preferences;
        mContext = context;
        mResolver = mContext.getContentResolver();
    }

    public long addUser(String name, String surname, int age, int gender, String location){

        String[] projectedColumns = {UserContract.UserEntry._ID};
        String selectedString = UserContract.UserEntry.COLUMN_USER_NAME + "=?";
        String locationSetting = mPreferences.getString("location","");;
        String[] selectionArgs = {locationSetting};
        Cursor userCursor;
        long locationId;
        userCursor = mResolver.query(UserContract.UserEntry.CONTENT_URI,
                projectedColumns,
                selectedString,
                selectionArgs,
                null);

        if (userCursor.moveToFirst()){
            int userIdIndex = userCursor.getColumnIndex(UserContract.UserEntry._ID);
            locationId = userCursor.getLong(userIdIndex);
        }
        else{
            Uri insertedUri;
            ContentValues userValues = new ContentValues();

            userValues.put(UserContract.UserEntry.COLUMN_USER_NAME, name);
            userValues.put(UserContract.UserEntry.COLUMN_USER_SURNAME, surname);
            userValues.put(UserContract.UserEntry.COLUMN_USER_AGE, age);
            userValues.put(UserContract.UserEntry.COLUMN_GENDER_KEY, gender);
            userValues.put(UserContract.UserEntry.COLUMN_LOCATION_KEY, location);

            insertedUri = mResolver.insert(UserContract.UserEntry.CONTENT_URI, userValues);

            locationId = ContentUris.parseId(insertedUri);
        }
        return locationId;
    }

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection   = null;
        BufferedReader reader          = null;
        String userJsonStr = null;

        String genderPref= mPreferences.getString("gender", "");
        String locationPref = mPreferences.getString("location","");
        String minAgePref = mPreferences.getString("minAge","");
        String maxAgePref = mPreferences.getString("maxAge","");

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

        try {
            getUserDataFromJson(userJsonStr);
            return userJsonStr;
        } catch (Exception e) {
            Log.e("UserTask", e.getMessage(), e);
        }


        return null;
    }

    private User[] getUserDataFromJson(String userJsonStr){

        Log.v("HomeActivity", userJsonStr);

        Gson gson = new Gson();

        return gson.fromJson(userJsonStr, User[].class);
    }

    @Override
    protected void onPostExecute(String userString) {
        super.onPostExecute(userString);

        User[] users = getUserDataFromJson(userString);

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
            long userId = addUser(users[i].name, users[i].surname, users[i].age,genderInput,users[i].location );
        }

        mAdapter.myDataset.clear();
        mAdapter.myDataset = Arrays.asList(users);

        mAdapter.setUserData(userString);
    }
}
