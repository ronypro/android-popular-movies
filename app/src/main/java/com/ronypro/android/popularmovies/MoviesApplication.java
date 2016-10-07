package com.ronypro.android.popularmovies;

import com.ronypro.android.mvp.MvpApplication;
import com.ronypro.android.mvp.model.Models;
import com.ronypro.android.mvp.presenter.Presenters;
import com.ronypro.android.popularmovies.model.MovieModel;
import com.ronypro.android.popularmovies.model.MovieModelImpl;
import com.ronypro.android.popularmovies.presenter.MovieDetailPresenter;
import com.ronypro.android.popularmovies.presenter.MovieDetailPresenterImpl;
import com.ronypro.android.popularmovies.presenter.MovieListPresenter;
import com.ronypro.android.popularmovies.presenter.MovieListPresenterImpl;

/**
 * Created by rahony on 07/10/16.
 */

public class MoviesApplication extends MvpApplication {

    @Override
    protected void configPresenters(Presenters presenters) {
        presenters.put(MovieListPresenter.class, MovieListPresenterImpl.class);
        presenters.put(MovieDetailPresenter.class, MovieDetailPresenterImpl.class);
    }

    @Override
    protected void configModels(Models models) {
        models.put(MovieModel.class, MovieModelImpl.class);
    }

}
