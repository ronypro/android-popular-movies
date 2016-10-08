package com.ronypro.android.popularmovies.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.ronypro.android.mvp.view.MvpAppCompatActivity;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.presenter.MovieListPresenter;
import com.ronypro.android.popularmovies.view.MovieListView;

import java.util.List;

public class MovieListActivity extends MvpAppCompatActivity<MovieListPresenter> implements MovieListView {

    private static final String TAG = MovieListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
    }

    @Override
    protected Class<MovieListPresenter> getPresenterClass() {
        return MovieListPresenter.class;
    }

    @Override
    protected int getLayoutToInflate() {
        return R.layout.activity_movie_list;
    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        //TODO:
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}
