package com.ronypro.android.popularmovies.presenter;

import com.ronypro.android.mvp.presenter.Presenter;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.view.MovieListView;

/**
 * Created by rahony on 07/10/16.
 */
public interface MovieListPresenter extends Presenter<MovieListView> {

    void onMovieClick(Movie movie);

}
