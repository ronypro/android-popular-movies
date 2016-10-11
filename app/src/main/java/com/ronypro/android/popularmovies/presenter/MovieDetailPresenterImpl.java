package com.ronypro.android.popularmovies.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ronypro.android.mvp.presenter.AbstractPresenter;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.view.MovieDetailView;

/**
 * Created by rahony on 07/10/16.
 */
public class MovieDetailPresenterImpl extends AbstractPresenter<MovieDetailView> implements MovieDetailPresenter {

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        Movie movie = extras.getParcelable(MovieDetailView.EXTRA_MOVIE);
        getView().showMovie(movie);
    }

}
