package com.ronypro.android.popularmovies.contract;

import android.net.Uri;

import com.ronypro.android.mvp.presenter.Presenter;
import com.ronypro.android.mvp.view.View;
import com.ronypro.android.popularmovies.entity.Movie;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieDetailContract {

    interface MovieDetailView extends View<MovieDetailPresenter> {

        String EXTRA_MOVIE = MovieDetailView.class.getName() + ".movie";

        void showMovie(Movie movie);

        void showPoster(Uri posterUri);

    }

    interface MovieDetailPresenter extends Presenter<MovieDetailView> {
    }


}
