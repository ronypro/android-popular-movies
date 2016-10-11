package com.ronypro.android.popularmovies.view.activity;

import android.support.v7.widget.Toolbar;

import com.ronypro.android.mvp.view.MvpAppCompatActivity;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.presenter.MovieDetailPresenter;
import com.ronypro.android.popularmovies.view.MovieDetailView;

public class MovieDetailActivity extends MvpAppCompatActivity<MovieDetailPresenter> implements MovieDetailView {

    @Override
    protected int getLayoutToInflate() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void onInflateLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected Class<MovieDetailPresenter> getPresenterClass() {
        return MovieDetailPresenter.class;
    }


    @Override
    public void showMovie(Movie movie) {

    }

}
