package com.ronypro.android.popularmovies.model.client;

import com.ronypro.android.popularmovies.entity.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieDatabaseApi {

    @GET("3/movie/{listType}")
    Call<MovieList> getMovieList(@Path("listType") String listType, @Query("api_key") String apiKey);

}
