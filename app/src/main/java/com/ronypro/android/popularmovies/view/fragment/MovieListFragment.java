package com.ronypro.android.popularmovies.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ronypro.android.mvp.view.MvpSupportFragment;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.contract.MovieListContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.MovieListType;
import com.ronypro.android.popularmovies.view.activity.MovieDetailActivity;
import com.ronypro.android.popularmovies.view.adapter.MovieListAdapter;

import java.util.List;

public class MovieListFragment
        extends MvpSupportFragment<MovieListContract.MovieListPresenter>
        implements MovieListContract.MovieListView,
                   MovieListAdapter.Holder {

    private MovieListAdapter movieListAdapter;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void onInflateLayout() {
        movieListAdapter = new MovieListAdapter(this);
        RecyclerView moviesRecyclerView = (RecyclerView) getView().findViewById(R.id.movies_recycler_view);
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setAdapter(movieListAdapter);
        setHasOptionsMenu(true);
    }

    @Override
    protected Class<MovieListContract.MovieListPresenter> getPresenterClass() {
        return MovieListContract.MovieListPresenter.class;
    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        movieListAdapter.setEntities(movieList);
    }

    @Override
    public void startDetailView(Bundle extras) {
        if (getHolder().isTwoPane()) {
            replaceDetailFragment(extras);
        } else {
            startDetailActivity(extras);
        }
    }

    private void replaceDetailFragment(Bundle extras) {
        MovieDetailFragment.replaceInContainer(
                getFragmentManager(),
                R.id.movie_detail_fragment_container,
                extras);
    }

    private void startDetailActivity(Bundle extras) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public boolean canShowMovieDetail() {
        Holder holder = getHolder();
        return holder != null && holder.isTwoPane();
    }

    @Override
    public Uri getPosterUri(Movie movie) {
        return getPresenter().getPosterUri(movie);
    }

    @Override
    public void onMovieClick(Movie movie) {
        getPresenter().onMovieClick(movie);
    }

    private Holder getHolder() {
        return (Holder) getActivity();
    }

    public void onListSelected(@MovieListType int movieListType) {
        getPresenter().onListSelected(movieListType);
    }

    public interface Holder {

        boolean isTwoPane();

    }

}
