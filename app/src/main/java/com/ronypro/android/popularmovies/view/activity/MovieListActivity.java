package com.ronypro.android.popularmovies.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ronypro.android.mvp.view.MvpAppCompatActivity;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.presenter.MovieListPresenter;
import com.ronypro.android.popularmovies.view.MovieListView;
import com.ronypro.android.popularmovies.view.adapter.MovieListAdapter;

import java.util.List;

public class MovieListActivity extends MvpAppCompatActivity<MovieListPresenter> implements MovieListView, MovieListAdapter.Holder {

    private MovieListAdapter movieListAdapter;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected void onInflateLayout() {
        movieListAdapter = new MovieListAdapter(this);
        RecyclerView moviesRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setAdapter(movieListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                getPresenter().onSettingsClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Class<MovieListPresenter> getPresenterClass() {
        return MovieListPresenter.class;
    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        movieListAdapter.setMovies(movieList);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startDetailView(Bundle extras) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void startSettingsView() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public Uri getPosterUri(Movie movie) {
        return getPresenter().getPosterUri(movie);
    }

    @Override
    public void onMovieClick(Movie movie) {
        getPresenter().onMovieClick(movie);
    }

}
