package com.ronypro.android.popularmovies.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.view.fragment.MovieListFragment;

public class MovieListActivity
        extends AppCompatActivity
        implements MovieListFragment.Holder {

    static {
        //hack to use a selector with vector drawable :(
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private boolean twoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        twoPane = findViewById(R.id.movie_detail_fragment_container) != null;
    }

    @Override
    public boolean isTwoPane() {
        return twoPane;
    }
}
