package com.ronypro.android.popularmovies.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahony on 02/12/16.
 */

public class ParcilUtil {

    public static <T> List<T> readTypedListOrNUll(Parcel parcel, Parcelable.Creator<T> creator) {
        List<T> result = new ArrayList<>();
        parcel.readTypedList(result, creator);
        if (result.isEmpty())
            return null;
        return result;
    }

}
