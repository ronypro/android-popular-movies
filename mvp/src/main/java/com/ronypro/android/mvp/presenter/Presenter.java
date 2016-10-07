package com.ronypro.android.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ronypro.android.mvp.view.View;


/**
 * Created by rahony on 05/07/16.
 */

public interface Presenter<T extends View> {

    void setApplicationContext(Context applicationContext);

    void setContext(Context context);

    void setView(T view);

    T getView();

    void onCreate(@NonNull Bundle extras, @Nullable Bundle savedInstanceState);

    void onRestart();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);
}
