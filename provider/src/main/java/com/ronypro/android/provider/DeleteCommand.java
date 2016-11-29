package com.ronypro.android.provider;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Rahony on 17/08/2016.
 */
public interface DeleteCommand {

    int delete(SQLiteDatabase database, Uri uri, String selection, String[] selectionArgs);

}
