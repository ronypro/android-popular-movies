package com.ronypro.android.mvp.model;

import android.content.Context;

import com.ronypro.android.mvp.SingletonClassMap;

/**
 * Created by rahony on 23/05/16.
 */
public class Models extends SingletonClassMap<Model> {

    private Context applicationContext;

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void onInstanceCreated(Model instance) {
        instance.setApplicationContext(applicationContext);
    }

}

