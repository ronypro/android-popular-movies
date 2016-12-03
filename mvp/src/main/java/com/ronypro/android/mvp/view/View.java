package com.ronypro.android.mvp.view;

import android.content.Intent;

import com.ronypro.android.mvp.presenter.Presenter;


/**
 * Created by rahony on 05/07/16.
 */

public interface View<T extends Presenter> {

    void setPresenter(T presenter);

    T getPresenter();

    void setResult(int result, Intent data);

    void showToast(int message);
}
