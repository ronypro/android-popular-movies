package com.ronypro.android.popularmovies.model.client;

import okhttp3.ResponseBody;

/**
 * Created by rahony on 07/10/16.
 */

public class HttpCallException extends Exception {

    private final int code;
    private final ResponseBody responseBody;

    public HttpCallException(int code, ResponseBody responseBody) {
        this.code = code;
        this.responseBody = responseBody;
    }

    public int getCode() {
        return code;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

}
