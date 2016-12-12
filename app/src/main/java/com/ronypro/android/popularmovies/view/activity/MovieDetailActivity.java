package com.ronypro.android.popularmovies.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.ronypro.android.popularmovies.view.adapter.VideoListAdapter;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.List;


public class MovieDetailActivity
        extends MvpAppCompatActivity<MovieDetailContract.MovieDetailPresenter>
        implements MovieDetailContract.MovieDetailView, View.OnClickListener, VideoListAdapter.Holder {

    private TextView originalTitleTextView;
    private RatingBar voteAverageRatingBar;
    private TextView releaseDateTextView;
    private TextView synopsisTextView;
    private ImageView posterImageView;
    private RecyclerView videosRecyclerView;

    private VideoListAdapter videoListAdapter;

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
        videosRecyclerView = (RecyclerView) findViewById(R.id.detail_movie_videos_list_recycler_view);

        videoListAdapter = new VideoListAdapter(this);
        videosRecyclerView.setAdapter(videoListAdapter);

        //TODO:
        findViewById(R.id.detail_movie_favorite_button).setOnClickListener(this);
        findViewById(R.id.detail_movie_unfavorite_button).setOnClickListener(this);
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

    @Override
    public void showVideoList(List<Video> videoList) {
        videoListAdapter.setVideos(videoList);
    }

    @Override
    public void showReviewList(List<Review> reviewList) {
        Toast.makeText(getBaseContext(), "REVIEW LIST SHOWED", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startPlayer(Uri playerUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, playerUri);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.detail_movie_favorite_button) {
            getPresenter().onFavoriteMovieClick();
        } else if (id == R.id.detail_movie_unfavorite_button) {
            getPresenter().onUnfavoriteMovieClick();
        }
    }

    @Override
    public Uri getThumbnailUri(Video video) {
        return getPresenter().getThumbnailUri(video);
    }

    @Override
    public void onVideoClick(Video video) {
        getPresenter().onVideoClick(video);
    }

}
