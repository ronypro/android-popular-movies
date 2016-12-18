package com.ronypro.android.database.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.ronypro.android.database.helper.EntityCursorHelper;

/**
 * Created by rahony on 12/12/16.
 */

public abstract class BaseEntityLoader<T>
        extends CursorLoader
        implements EntityLoader<T> {

    private EntityCursorHelper<T> entityCursorHelper;

    public BaseEntityLoader(Context context, EntityCursorHelper entityCursorHelper) {
        super(context);
        this.entityCursorHelper = entityCursorHelper;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        entityCursorHelper.setCursor(data);
        onLoadFinished(loader, entityCursorHelper);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    protected String[] getProjectionFromHelper() {
        return entityCursorHelper.getProjection();
    }

    public abstract void onLoadFinished(Loader<Cursor> loader, EntityCursorHelper<T> data);

}
