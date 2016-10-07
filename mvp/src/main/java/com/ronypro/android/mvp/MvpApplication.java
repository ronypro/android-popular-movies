package com.ronypro.android.mvp;

import android.app.Application;

import com.ronypro.android.mvp.model.Models;
import com.ronypro.android.mvp.presenter.Presenters;

/**
 * Created by Rahony on 29/08/2016.
 */
public abstract class MvpApplication extends Application {

    public static MvpApplication instance;

    public static MvpApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Mvp mvp = Mvp.getInstance();
        mvp.setApplicationContext(this);
        configPresenters(mvp.getPresenters());
        configModels(mvp.getModels());
    }

    protected abstract void configPresenters(Presenters presenters);

    protected abstract void configModels(Models models);

}
