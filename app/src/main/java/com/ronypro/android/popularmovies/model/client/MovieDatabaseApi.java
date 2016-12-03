package com.ronypro.android.popularmovies.model.client;

import com.ronypro.android.popularmovies.entity.MovieList;
import com.ronypro.android.popularmovies.entity.ReviewList;
import com.ronypro.android.popularmovies.entity.VideoList;

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

    @GET("3/movie/{movie_id}/videos")
    Call<VideoList> getVideoList(@Path("movie_id") long movieId, @Query("api_key") String apiKey);

    @GET("3/movie/{movie_id}/reviews")
    Call<ReviewList> getReviewList(@Path("movie_id") long movieId, @Query("api_key") String apiKey);

}
