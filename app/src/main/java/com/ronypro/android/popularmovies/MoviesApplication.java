package com.ronypro.android.popularmovies;

import com.ronypro.android.mvp.MvpApplication;
import com.ronypro.android.mvp.model.Models;
import com.ronypro.android.mvp.presenter.Presenters;
import com.ronypro.android.popularmovies.contract.MovieDetailContract;
import com.ronypro.android.popularmovies.contract.MovieListContract;
import com.ronypro.android.popularmovies.contract.model.MovieModel;
import com.ronypro.android.popularmovies.model.MovieModelImpl;
import com.ronypro.android.popularmovies.contract.model.ReviewModel;
import com.ronypro.android.popularmovies.model.ReviewModelImpl;
import com.ronypro.android.popularmovies.contract.model.VideoModel;
import com.ronypro.android.popularmovies.model.VideoModelImpl;
import com.ronypro.android.popularmovies.presenter.MovieDetailPresenterImpl;
import com.ronypro.android.popularmovies.presenter.MovieListPresenterImpl;

/**
 * Created by rahony on 07/10/16.
 */

public class MoviesApplication extends MvpApplication {

    @Override
    protected void configPresenters(Presenters presenters) {
        presenters.put(MovieListContract.MovieListPresenter.class, MovieListPresenterImpl.class);
        presenters.put(MovieDetailContract.MovieDetailPresenter.class, MovieDetailPresenterImpl.class);
    }

    @Override
    protected void configModels(Models models) {
        models.put(MovieModel.class, MovieModelImpl.class);
        models.put(ReviewModel.class, ReviewModelImpl.class);
        models.put(VideoModel.class, VideoModelImpl.class);
    }

}
