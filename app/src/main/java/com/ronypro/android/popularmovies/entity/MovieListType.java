package com.ronypro.android.popularmovies.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        MovieListType.POPULAR,
        MovieListType.TOP_RATED,
        MovieListType.FAVORITE,
})
public @interface MovieListType {

    int POPULAR = 0;
    int TOP_RATED = 1;
    int FAVORITE = 2;

}