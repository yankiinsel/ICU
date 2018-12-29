package com.icu.yankiinsel.icu;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class UserContract {

    public static final String CONTENT_AUTHORITY = "com.icu.yankiinsel.icu";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_USER = "user";

    public static final class UserEntry implements BaseColumns
    {
        public static String TABLE_NAME = "user";
        public static String COLUMN_USER_NAME = "name";
        public static String COLUMN_USER_SURNAME = "surname";
        public static String COLUMN_USER_AGE = "age";
        public static String COLUMN_GENDER_KEY = "gender_id";
        public static String COLUMN_LOCATION_KEY = "location_id";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_USER)
                .build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildUserUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendPath(PATH_USER)
                    .appendQueryParameter(COLUMN_USER_NAME, name)
                    .build();
        }

        public static String getUserNameFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }

    public static final class GenderEntry implements BaseColumns
    {
        public static String TABLE_NAME  = "gender";
        public static String COLUMN_GENDER = "name";
    }

    public static final class LocationEntry implements BaseColumns
    {
        public static String TABLE_NAME  = "location";
        public static String COLUMN_LOCATION = "name";
    }
}
