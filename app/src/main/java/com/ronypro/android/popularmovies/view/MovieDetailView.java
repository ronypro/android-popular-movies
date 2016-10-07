package com.ronypro.android.popularmovies.view;

import com.ronypro.android.mvp.view.View;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.presenter.MovieDetailPresenter;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieDetailView extends View<MovieDetailPresenter> {

    void showMovie(Movie movie);

}