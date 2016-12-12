package com.ronypro.android.popularmovies.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rahony on 02/12/16.
 */

public class ParcilUtil {

    private static final byte TRUE_VALUE = 1;
    private static final byte FALSE_VALUE = 0;

    public static <T> List<T> readTypedListOrNUll(Parcel parcel, Parcelable.Creator<T> creator) {
        List<T> result = new ArrayList<>();
        parcel.readTypedList(result, creator);
        if (result.isEmpty())
            return null;
        return result;
    }

    public static Date readDate(Parcel in) {
        long date = in.readLong();
        if (date == Long.MIN_VALUE)
            return null;
        return new Date(date);
    }

    public static boolean readBool(Parcel in) {
        return in.readByte() == TRUE_VALUE;
    }

    public static void writeDate(Parcel parcel, Date date) {
        if (date == null) {
            parcel.writeLong(Long.MIN_VALUE);
        } else {
            parcel.writeLong(date.getTime());
        }
    }

    public static void writeBool(Parcel parcel, boolean bool) {
        if (bool) {
            parcel.writeByte(TRUE_VALUE);
        } else {
            parcel.writeByte(FALSE_VALUE);
        }
    }
}
