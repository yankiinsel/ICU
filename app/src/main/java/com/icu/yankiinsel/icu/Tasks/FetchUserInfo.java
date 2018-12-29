package com.icu.yankiinsel.icu.Tasks;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.icu.yankiinsel.icu.Adapters.HomeRecyclerAdapter;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchUserInfo extends AsyncTask<String, Void, String> {

    HomeRecyclerAdapter mAdapter;
    SharedPreferences mPreferences;

    public FetchUserInfo(HomeRecyclerAdapter adapter, SharedPreferences preferences) {
        mAdapter   = adapter;
        mPreferences = preferences;

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

    private void getUserDataFromJson(String userJsonStr)
            throws JSONException {

        Log.v("HomeActivity", userJsonStr);

    }

    @Override
    protected void onPostExecute(String userString) {
        super.onPostExecute(userString);
        try {
            getUserDataFromJson(userString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mAdapter.setUserData(userString);
    }
}
