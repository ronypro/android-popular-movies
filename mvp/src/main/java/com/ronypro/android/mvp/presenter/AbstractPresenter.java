package com.ronypro.android.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.ronypro.android.mvp.Mvp;
import com.ronypro.android.mvp.model.Model;
import com.ronypro.android.mvp.view.View;

/**
 * Created by rahony on 05/07/16.
 */

public abstract class AbstractPresenter<T extends View> implements Presenter<T> {

    private Context applicationContext;
    private Context context;
    private T view;

    private boolean started;

    @Override
    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected Context getApplicationContext() {
        return applicationContext;
    }

    protected Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setView(T view) {
        this.view = view;
    }

    @Override
    public T getView() {
        return view;
    }

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {

    }

    @Override
    public void onRestart() {
    }

    @CallSuper
    @Override
    public void onStart() {
        started = true;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @CallSuper
    @Override
    public void onStop() {
        started = false;
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    protected <M extends Model> M getModel(Class<M> abstractModelclass){
        return Mvp.getInstance().getModels().get(abstractModelclass);
    }

    public boolean isStarted() {
        return started;
    }

}
