package com.ronypro.android.popularmovies.view;

import android.net.Uri;

import com.ronypro.android.mvp.view.View;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.presenter.MovieDetailPresenter;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieDetailView extends View<MovieDetailPresenter> {

    String EXTRA_MOVIE = MovieDetailView.class.getName() + ".movie";

    void showMovie(Movie movie);

    void showPoster(Uri posterUri);

}
