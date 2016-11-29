package com.ronypro.android.provider;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Rahony on 17/08/2016.
 */
public interface UpdateCommand {
    int update(SQLiteDatabase database, Uri uri, ContentValues values, String selection, String[] selectionArgs);
}
