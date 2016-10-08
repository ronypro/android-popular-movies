package com.ronypro.android.popularmovies.model;

import com.ronypro.android.mvp.model.AbstractModel;
import com.ronypro.android.popularmovies.BuildConfig;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.MovieList;
import com.ronypro.android.popularmovies.entity.MovieListType;
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

    private MovieDatabaseApi movieDatabaseApi;

    public MovieModelImpl() {
        this.movieDatabaseApi = MovieDatabaseApiUtil.getApiInstance();
    }

    @Override
    public List<Movie> getMovieList(@MovieListType String type) throws HttpCallException, NetworkCallException {
        String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;
        Call<MovieList> movieListCall = movieDatabaseApi.getMovieList(type, apiKey);
        MovieList movieList = MovieDatabaseApiUtil.resolveCall(movieListCall);
        return movieList.results;
    }

}
