package com.ronypro.android.mvp.model;

import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by rahony on 14/06/16.
 */

public abstract class AbstractModel implements Model {

    private Context applicationContext;

    @Override
    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected Context getApplicationContext() {
        return applicationContext;
    }

    protected ContentResolver getContentResolver() {
        return getApplicationContext().getContentResolver();
    }

}
