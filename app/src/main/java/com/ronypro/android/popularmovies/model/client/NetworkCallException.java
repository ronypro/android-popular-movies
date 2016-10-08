package com.ronypro.android.popularmovies.model.client;

import java.io.IOException;

/**
 * Created by rahony on 07/10/16.
 */

public class NetworkCallException extends Exception {

    public NetworkCallException(IOException e) {
        super(e);
    }

    public IOException getIOException() {
        return (IOException) getCause();
    }

}
