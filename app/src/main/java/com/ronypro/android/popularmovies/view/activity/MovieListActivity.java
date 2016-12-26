package com.ronypro.android.popularmovies.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;

import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.MovieListType;
import com.ronypro.android.popularmovies.view.fragment.MovieListFragment;

public class MovieListActivity
        extends AppCompatActivity
        implements MovieListFragment.Holder, NavigationView.OnNavigationItemSelectedListener {

    static {
        //hack to use a selector with vector drawable :(
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private boolean twoPane;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private MovieListFragment movieListFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        twoPane = findViewById(R.id.movie_detail_fragment_container) != null;

        movieListFragment = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.movie_list_fragment);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            configMenu(actionBar);
        }
    }

    private void configMenu(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                drawerToggle.syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerToggle.syncState();
            }

        };
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null && drawerLayout != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean isTwoPane() {
        return twoPane;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle != null && drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        sendEvent(item);
        return true;
    }

    private void sendEvent(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_item_popular:
                movieListFragment.onListSelected(MovieListType.POPULAR);
                break;
            case R.id.nav_item_top_rated:
                movieListFragment.onListSelected(MovieListType.TOP_RATED);
                break;
            case R.id.nav_item_favorite:
                movieListFragment.onListSelected(MovieListType.FAVORITE);
                break;
        }
    }

}
