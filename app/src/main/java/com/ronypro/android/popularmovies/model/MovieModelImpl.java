package com.ronypro.android.popularmovies.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.ronypro.android.mvp.model.AbstractModel;
import com.ronypro.android.popularmovies.BuildConfig;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.MovieList;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApi;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApiUtil;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;

import java.util.List;

import retrofit2.Call;

/**
 * Created by rahony on 07/10/16.
 */

public class MovieModelImpl extends AbstractModel implements MovieModel {

    public static final String BASE_POSTER_URI = "http://image.tmdb.org/t/p/";
    public static final String POSTER_SIZE = "w154";
    private MovieDatabaseApi movieDatabaseApi;

    public MovieModelImpl() {
        this.movieDatabaseApi = MovieDatabaseApiUtil.getApiInstance();
    }

    @Override
    public List<Movie> getMovieList() throws HttpCallException, NetworkCallException {
        String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;
        String type = getListType();
        Call<MovieList> movieListCall = movieDatabaseApi.getMovieList(type, apiKey);
        MovieList movieList = MovieDatabaseApiUtil.resolveCall(movieListCall);
        return movieList.results;
    }

    private String getListType() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String listTypeKey = context.getString(R.string.pref_movie_list_type_key);
        String listTypeDefault = context.getString(R.string.pref_movie_list_type_value);
        return sharedPreferences.getString(listTypeKey, listTypeDefault);
    }

    @Override
    public Uri getPosterUri(Movie movie) {
        return Uri.parse(BASE_POSTER_URI)
                .buildUpon()
                .appendPath(POSTER_SIZE)
                .appendEncodedPath(movie.posterPath)
                .build();
    }
}
