package com.ronypro.android.popularmovies.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({
        MovieListType.POPULAR,
        MovieListType.TOP_RATED
})
public @interface MovieListType {

    String POPULAR = "popular";
    String TOP_RATED = "top_rated";

}
