package com.ronypro.android.popularmovies.presenter;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ronypro.android.mvp.Mvp;
import com.ronypro.android.mvp.presenter.AbstractPresenter;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.contract.MovieDetailContract;
import com.ronypro.android.popularmovies.contract.MovieListContract;
import com.ronypro.android.popularmovies.contract.loader.MovieListLoader;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.contract.model.MovieModel;
import com.ronypro.android.popularmovies.entity.MovieListType;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.presenter.asynctask.MovieListAsyncTask;

import java.util.List;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

import static android.content.ContentValues.TAG;

/**
 * Created by rahony on 07/10/16.
 */
public class MovieListPresenterImpl
        extends AbstractPresenter<MovieListContract.MovieListView>
        implements MovieListContract.MovieListPresenter, MovieListAsyncTask.Callback, MovieListLoader.Callback {

    private static final int MOVIE_LOADER = 1;

    private static final int FIRST_MOVIE_INDEX = 0;

    private static final String MOVIE_LIST_TYPE_STATE = "movieListType";

    private MovieModel movieModel = Mvp.getModel(MovieModel.class);
    @MovieListType
    private int movieListType = MovieListType.POPULAR;
    private boolean needShowFirstMovie;

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        super.onCreate(extras, savedInstanceState);
        SQLiteStudioService.instance().start(getContext());
        needShowFirstMovie = savedInstanceState == null && getView().canShowMovieDetail();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SQLiteStudioService.instance().stop();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadMovieList();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(MOVIE_LIST_TYPE_STATE, movieListType);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //"cast" to not show error in code editor
        @MovieListType
        int movieListTypeCast = savedInstanceState.getInt(MOVIE_LIST_TYPE_STATE);
        movieListType = movieListTypeCast;
    }

    private void loadMovieList() {
        if (movieModel.needLoaderToList(movieListType)) {
            MovieListLoader movieLoader = movieModel.getMovieListLoader(getContext(), this);
            getLoaderManager().initLoader(MOVIE_LOADER, null, movieLoader);
        } else {
            MovieListAsyncTask.executeParallel(movieListType, this);
        }
    }

    @Override
    public void onMovieListResult(List<Movie> movieList) {
        onMovieListResult(movieList, false);
    }

    public void onMovieListResult(List<Movie> movieList, boolean fromLoader) {
        MovieListContract.MovieListView view = getView();
        view.showMovieList(movieList);
        if (needShowFirstMovie) {
            if (!movieList.isEmpty()) {
                Movie movie = movieList.get(FIRST_MOVIE_INDEX);
                showMovieDetail(movie, fromLoader);
            } else {
                clearMovieDetail(fromLoader);
            }
        }
    }

    @Override
    public void onMovieListHttpCallException(HttpCallException httpCallException) {
        Log.e(TAG, "Error " + httpCallException.getCode() + " in request movie list", httpCallException);
        getView().showToast(R.string.movie_list_load_http_error);
    }

    @Override
    public void onMovieListNetworkCallException(NetworkCallException networkCallException) {
        Log.e(TAG, "Network error in request movie list", networkCallException);
        getView().showToast(R.string.network_error);
    }

    @Override
    public Uri getPosterUri(Movie movie) {
        return movieModel.getPosterThumbnailUri(movie);
    }

    @Override
    public void onMovieClick(Movie movie) {
        showMovieDetail(movie, false);
    }

    @Override
    public void onMovieListLoaded(List<Movie> movies) {
        onMovieListResult(movies, true);
    }

    private void showMovieDetail(Movie movie, boolean fromLoader) {
        Bundle extras = new Bundle();
        extras.putParcelable(MovieDetailContract.MovieDetailView.EXTRA_MOVIE, movie);
        getView().startDetailView(extras, fromLoader);
    }

    private void clearMovieDetail(boolean fromLoader) {
        getView().clearMovieDetail(fromLoader);
    }

    @Override
    public void onListSelected(@MovieListType int movieListType) {
        boolean listChanged = this.movieListType != movieListType;
        this.movieListType = movieListType;
        if (listChanged) {
            needShowFirstMovie = getView().canShowMovieDetail();
            loadMovieList();
        }
    }
}
