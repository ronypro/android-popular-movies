package com.ronypro.android.popularmovies.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by rahony on 07/10/16.
 */

public class Movie {

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("overview")
    public String overview;

    @SerializedName("vote_average")
    public String voteAverage;

    @SerializedName("release_date")
    public Date releaseDate;

}
