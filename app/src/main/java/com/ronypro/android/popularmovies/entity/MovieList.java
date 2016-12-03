package com.ronypro.android.popularmovies.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public class MovieList {

    @Expose
    @SerializedName("results")
    public List<Movie> results;

}
