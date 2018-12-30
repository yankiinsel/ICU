package com.icu.yankiinsel.icu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ICU.db";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry._ID + " INTEGER PRIMARY KEY," +
                UserContract.UserEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_SURNAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_AGE + " INTEGER NOT NULL, " +
                UserContract.UserEntry.COLUMN_GENDER_ID + " INTEGER NOT NULL, " +
                UserContract.UserEntry.COLUMN_LOCATION + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + UserContract.UserEntry.COLUMN_GENDER_ID + ") REFERENCES " +
                UserContract.GenderEntry.TABLE_NAME + " (" + UserContract.GenderEntry._ID + ") " +
                " )";

        final String SQL_CREATE_GENDER_TABLE = "CREATE TABLE IF NOT EXISTS " + UserContract.GenderEntry.TABLE_NAME + " (" +
                UserContract.GenderEntry._ID + " INTEGER PRIMARY KEY," +
                UserContract.GenderEntry.COLUMN_GENDER_NAME + " TEXT UNIQUE NOT NULL " +
                " )";

        db.execSQL(SQL_CREATE_GENDER_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.GenderEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        onCreate(db);
    }
}
