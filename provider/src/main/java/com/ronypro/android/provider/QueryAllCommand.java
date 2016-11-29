package com.ronypro.android.provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by Rahony on 17/08/2016.
 */
public interface QueryAllCommand {

    @NonNull
    Cursor queryAll(SQLiteDatabase database, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);

}
