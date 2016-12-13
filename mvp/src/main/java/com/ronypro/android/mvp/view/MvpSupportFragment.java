package com.ronypro.android.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ronypro.android.mvp.Mvp;
import com.ronypro.android.mvp.presenter.Presenter;
import com.ronypro.android.mvp.presenter.Presenters;

public abstract class MvpSupportFragment<T extends Presenter> extends Fragment implements View<T> {

    private T presenter;

    private boolean started;

    public MvpSupportFragment() {
    }

    private void onRestart() {
        presenter.onRestart();
    }

    @CallSuper
    @Override
    public void onStart() {
        if (started)
            onRestart();
        super.onStart();
        started = true;
        presenter.onStart();
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @CallSuper
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        int layout = getLayoutToInflate();
        android.view.View view = inflater.inflate(layout, container, false);
        return view;
    }

    @CallSuper
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectPresenter();
        onInflateLayout();
        callPresenterOnCreate(savedInstanceState);
        if (savedInstanceState != null)
            presenter.onRestoreInstanceState(savedInstanceState);
    }

    private void injectPresenter() {
        Class<T> presenterClass = getPresenterClass();
        Mvp mvp = Mvp.getInstance();
        Presenters presenters = mvp.getPresenters();
        T presenter = presenters.get(presenterClass);
        presenter.setContext(getContext());
        presenter.setLoaderManager(getLoaderManager());
        setPresenter(presenter);
        presenter.setView(this);
    }

    protected abstract Class<T> getPresenterClass();

    protected abstract int getLayoutToInflate();

    protected void onInflateLayout() {
    }

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public T getPresenter() {
        return presenter;
    }

    @Override
    public void setResult(int result, Intent data) {
        Activity activity = getActivity();
        if (activity != null)
            activity.setResult(result, data);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    private void callPresenterOnCreate(Bundle savedInstanceState) {
        Bundle extras = getArguments();
        if (extras == null)
            extras = new Bundle();
        presenter.onCreate(extras, savedInstanceState);
    }

}
