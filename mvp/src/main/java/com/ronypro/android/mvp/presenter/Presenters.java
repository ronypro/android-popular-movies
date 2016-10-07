package com.ronypro.android.mvp.presenter;

import android.content.Context;

import com.ronypro.android.mvp.ConstructorClassMap;

/**
 * Created by rahony on 23/05/16.
 */
public class Presenters extends ConstructorClassMap<Presenter> {

    private Context applicationContext;

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void onInstanceCreated(Presenter instance) {
        instance.setApplicationContext(applicationContext);
    }

}

