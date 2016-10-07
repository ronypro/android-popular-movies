package com.ronypro.android.mvp;

import android.content.Context;

import com.ronypro.android.mvp.model.Model;
import com.ronypro.android.mvp.model.Models;
import com.ronypro.android.mvp.presenter.Presenter;
import com.ronypro.android.mvp.presenter.Presenters;

/**
 * Created by Rahony on 29/08/2016.
 */
public class Mvp {

    private static Mvp instance;

    private Presenters presenters;
    private Models models;

    private Mvp() {
        presenters = new Presenters();
        models = new Models();
    }

    public Presenters getPresenters() {
        return presenters;
    }

    public Models getModels() {
        return models;
    }

    public void setApplicationContext(Context applicationContext) {
        presenters.setApplicationContext(applicationContext);
        models.setApplicationContext(applicationContext);
    }

    public static Mvp getInstance() {
        if (instance == null)
            instance = new Mvp();
        return instance;
    }

    public static <T extends Model> T getModel(Class<T> abstractModelClass) {
        return getInstance().getModels().get(abstractModelClass);
    }

    public static <T extends Presenter> T getPresenter(Class<T> abstractPresenterClass) {
        return getInstance().getPresenters().get(abstractPresenterClass);
    }

}
