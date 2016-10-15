package com.ronypro.android.popularmovies.view.activity;

import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ronypro.android.mvp.view.MvpAppCompatActivity;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.contract.MovieDetailContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;

;


public class MovieDetailActivity
        extends MvpAppCompatActivity<MovieDetailContract.MovieDetailPresenter>
        implements MovieDetailContract.MovieDetailView {

    private TextView originalTitleTextView;
    private RatingBar voteAverageRatingBar;
    private TextView releaseDateTextView;
    private TextView synopsisTextView;
    private ImageView posterImageView;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void onInflateLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        originalTitleTextView = (TextView) findViewById(R.id.detail_movie_original_title_textview);
        voteAverageRatingBar = (RatingBar) findViewById(R.id.detail_movie_vote_average_ratingbar);
        releaseDateTextView = (TextView) findViewById(R.id.detail_movie_release_date_textview);
        synopsisTextView = (TextView) findViewById(R.id.detail_movie_synopsis_textview);
        posterImageView = (ImageView) findViewById(R.id.detail_movie_poster_imageview);
    }

    @Override
    protected Class<MovieDetailContract.MovieDetailPresenter> getPresenterClass() {
        return MovieDetailContract.MovieDetailPresenter.class;
    }

    @Override
    public void showMovie(Movie movie) {
        originalTitleTextView.setText(movie.originalTitle);
        voteAverageRatingBar.setRating(movie.voteAverage / 2f);
        if (movie.releaseDate != null) {
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String formatedDate = dateFormat.format(movie.releaseDate);
            releaseDateTextView.setText(formatedDate);
        } else {
            releaseDateTextView.setText(R.string.detail_movie_release_date_undefined);
        }
        if (movie.overview != null) {
            synopsisTextView.setText(movie.overview);
        } else {
            synopsisTextView.setText(R.string.detail_movie_synopsis_undefined);
        }
    }

    @Override
    public void showPoster(Uri posterUri) {
        Picasso.with(getBaseContext())
                .load(posterUri)
                .into(posterImageView);
    }
}
