package com.ronypro.android.popularmovies.contract;

import android.net.Uri;
import android.os.Bundle;

import com.ronypro.android.mvp.presenter.Presenter;
import com.ronypro.android.mvp.view.View;
import com.ronypro.android.popularmovies.entity.Movie;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieListContract {

    interface MovieListView extends View<MovieListPresenter> {

        void showMovieList(List<Movie> movieList);

        void startDetailView(Bundle extras);

        void startSettingsView();

        boolean canShowMovieDetail();
    }

    interface MovieListPresenter extends Presenter<MovieListView> {

        void onMovieClick(Movie movie);

        Uri getPosterUri(Movie movie);

        void onSettingsClick();
    }

}
