package com.ronypro.android.mvp.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import com.ronypro.android.mvp.Mvp;
import com.ronypro.android.mvp.presenter.Presenter;
import com.ronypro.android.mvp.presenter.Presenters;

public abstract class MvpAppCompatActivity<T extends Presenter> extends AppCompatActivity implements View<T> {

    private T presenter;

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectPresenter();
        super.onCreate(savedInstanceState);
        inflateLayout();
        callPresenterOnCreate(savedInstanceState);
    }

    @CallSuper
    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.onRestart();
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter.onRestoreInstanceState(savedInstanceState);
    }

    private void injectPresenter() {
        Class<T> presenterClass = getPresenterClass();
        Mvp mvp = Mvp.getInstance();
        Presenters presenters = mvp.getPresenters();
        T presenter = presenters.get(presenterClass);
        presenter.setContext(this);
        setPresenter(presenter);
        //noinspection unchecked
        presenter.setView(this);
    }

    private void inflateLayout() {
        int layoutRes = getLayoutToInflate();
        setContentView(layoutRes);
        onInflateLayout();
    }

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public T getPresenter() {
        return presenter;
    }

    protected abstract Class<T> getPresenterClass();

    protected abstract int getLayoutToInflate();

    private void callPresenterOnCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras == null)
            extras = new Bundle();
        presenter.onCreate(extras, savedInstanceState);
    }

    protected void onInflateLayout() {
    }

}
