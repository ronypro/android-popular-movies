package com.ronypro.android.database.loader;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;

/**
 * Created by rahony on 12/12/16.
 */

public interface EntityLoader<T> extends LoaderManager.LoaderCallbacks<Cursor> {
}
