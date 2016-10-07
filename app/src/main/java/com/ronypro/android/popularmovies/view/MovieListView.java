package com.ronypro.android.popularmovies.view;

import com.ronypro.android.mvp.view.View;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.presenter.MovieListPresenter;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieListView extends View<MovieListPresenter> {

    void showMovieList(List<Movie> movieList);

}
