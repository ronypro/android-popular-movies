package com.ronypro.android.popularmovies.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ronypro.android.mvp.presenter.AbstractPresenter;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.MovieListType;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.presenter.asynctask.MovieListAsyncTask;
import com.ronypro.android.popularmovies.view.MovieListView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by rahony on 07/10/16.
 */
public class MovieListPresenterImpl extends AbstractPresenter<MovieListView> implements MovieListPresenter, MovieListAsyncTask.Callback {

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        super.onCreate(extras, savedInstanceState);
        loadMovieList();
    }

    private void loadMovieList() {
        MovieListAsyncTask.executeParallel(this, MovieListType.POPULAR);
    }

    @Override
    public void onMovieListResult(List<Movie> movieList) {
        getView().showMovieList(movieList);
    }

    @Override
    public void onMovieListHttpCallException(HttpCallException httpCallException) {
        Log.e(TAG, "Error " + httpCallException.getCode() + " in request movie list", httpCallException);
        getView().showToast(R.string.movie_list_load_http_error);
    }

    @Override
    public void onMovieListNetworkCallException(NetworkCallException networkCallException) {
        Log.e(TAG, "Network error in request movie list", networkCallException);
        getView().showToast(R.string.movie_list_load_network_error);
    }

    @Override
    public void onMovieClick(Movie movie) {
        //TODO:
    }

}
