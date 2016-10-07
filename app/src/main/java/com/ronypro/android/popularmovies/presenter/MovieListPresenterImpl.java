package com.ronypro.android.popularmovies.presenter;

import com.ronypro.android.mvp.presenter.AbstractPresenter;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.view.MovieListView;

/**
 * Created by rahony on 07/10/16.
 */
public class MovieListPresenterImpl extends AbstractPresenter<MovieListView> implements MovieListPresenter {

    @Override
    public void onMovieClick(Movie movie) {

    }

}
