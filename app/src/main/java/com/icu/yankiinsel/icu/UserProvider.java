package com.icu.yankiinsel.icu;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.icu.yankiinsel.icu.Model.User;

public class UserProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private UserDBHelper mOpenHelper;

    public static final int USER = 300;

    public static UriMatcher buildUriMatcher(){
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = UserContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, UserContract.PATH_USER, USER);
        return matcher;
    }

    private static final SQLiteQueryBuilder sUserbyNameQueryBuilder;

    static{
        sUserbyNameQueryBuilder = new SQLiteQueryBuilder();

        sUserbyNameQueryBuilder.setTables(
                UserContract.UserEntry.TABLE_NAME + " INNER JOIN " +
                        UserContract.GenderEntry.TABLE_NAME +
                        " ON " + UserContract.UserEntry.TABLE_NAME +
                        "." + UserContract.UserEntry.COLUMN_GENDER_KEY +
                        " = " + UserContract.GenderEntry.TABLE_NAME +
                        "." + UserContract.GenderEntry._ID);
    }

    public UserProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new UserDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        Cursor retCursor;
        switch(match){
            case USER: {
                retCursor = db.query(UserContract.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
            } break;
            default:{
                throw new UnsupportedOperationException("Unknown uri:" + uri);
            }
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch(match){
            case USER: {
                long _id = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = UserContract.UserEntry.buildUserUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into" + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }
}
