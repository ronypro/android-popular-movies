package com.ronypro.android.provider;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Rahony on 17/08/2016.
 */
public interface InsertCommand {

    long insert(SQLiteDatabase database, Uri uri, ContentValues values);

}
