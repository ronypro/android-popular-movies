package com.ronypro.android.mvp;

/**
 * Created by rahony on 14/06/16.
 */

public class MvpInstantiationException extends RuntimeException {

    public MvpInstantiationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

}
