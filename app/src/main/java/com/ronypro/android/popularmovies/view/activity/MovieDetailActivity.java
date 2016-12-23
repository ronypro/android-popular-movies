package com.ronypro.android.popularmovies.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ronypro.android.mvp.view.MvpAppCompatActivity;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.contract.MovieDetailContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.view.adapter.ReviewListAdapter;
import com.ronypro.android.popularmovies.view.adapter.VideoListAdapter;
import com.ronypro.android.popularmovies.view.fragment.MovieDetailFragment;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.List;


public class MovieDetailActivity
        extends AppCompatActivity {

    static {
        //hack to use a selector with vector drawable :(
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        MovieDetailFragment.replaceInContainer(
                getSupportFragmentManager(),
                R.id.movie_detail_fragment_container,
                getIntent().getExtras());
    }

}
