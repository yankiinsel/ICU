package com.icu.yankiinsel.icu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "weather.db";

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry._ID + " INTEGER PRIMARY KEY," +
                UserContract.UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_USER_SURNAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_USER_AGE + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + UserContract.UserEntry.COLUMN_GENDER_KEY + ") REFERENCES " +
                UserContract.GenderEntry.TABLE_NAME + " (" + UserContract.GenderEntry._ID + "), " +
                " FOREIGN KEY (" + UserContract.UserEntry.COLUMN_LOCATION_KEY + ") REFERENCES " +
                UserContract.LocationEntry.TABLE_NAME + " (" + UserContract.LocationEntry._ID + "));";

         final String SQL_CREATE_GENDER_TABLE =
                "CREATE TABLE " + UserContract.GenderEntry.TABLE_NAME
                        + " (" + UserContract.GenderEntry._ID + " INTEGER PRIMARY KEY,"
                        + UserContract.GenderEntry.COLUMN_GENDER + " TEXT NOT NULL);";

         final String SQL_CREATE_LOCATION_TABLE =
                "CREATE TABLE " + UserContract.LocationEntry.TABLE_NAME
                        + " (" + UserContract.LocationEntry._ID + " INTEGER PRIMARY KEY,"
                        + UserContract.LocationEntry.COLUMN_LOCATION + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_GENDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserContract.LocationEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserContract.GenderEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
