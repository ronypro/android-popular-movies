package com.ronypro.android.popularmovies.presenter;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ronypro.android.mvp.Mvp;
import com.ronypro.android.mvp.presenter.AbstractPresenter;
import com.ronypro.android.popularmovies.contract.MovieDetailContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.model.MovieModel;

/**
 * Created by rahony on 07/10/16.
 */
public class MovieDetailPresenterImpl
        extends AbstractPresenter<MovieDetailContract.MovieDetailView>
        implements MovieDetailContract.MovieDetailPresenter {

    private MovieModel movieModel = Mvp.getModel(MovieModel.class);

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        Movie movie = extras.getParcelable(MovieDetailContract.MovieDetailView.EXTRA_MOVIE);
        getView().showMovie(movie);
        Uri posterUri = movieModel.getPosterUri(movie);
        getView().showPoster(posterUri);
    }

}
