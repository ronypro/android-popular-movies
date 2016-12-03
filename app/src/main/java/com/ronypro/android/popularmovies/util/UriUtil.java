package com.ronypro.android.popularmovies.util;

import android.net.Uri;
import android.renderscript.Long2;

/**
 * Created by rahony on 03/12/16.
 */

public class UriUtil {

    private static final int DEFAULT_VALUE_POSITION_IN_URI = 1;

    public static String getStringFromUri(Uri uri, int position) {
        return uri.getPathSegments().get(position);
    }

    public static long getLongFromUri(Uri uri, int position) {
        String string = getStringFromUri(uri, position);
        return Long.parseLong(string);
    }

    public static long getLongFromUri(Uri uri) {
        return getLongFromUri(uri, DEFAULT_VALUE_POSITION_IN_URI);
    }

}
