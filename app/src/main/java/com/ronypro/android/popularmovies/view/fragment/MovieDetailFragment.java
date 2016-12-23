package com.ronypro.android.popularmovies.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ronypro.android.mvp.view.MvpAppCompatActivity;
import com.ronypro.android.mvp.view.MvpSupportFragment;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.contract.MovieDetailContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.view.adapter.ReviewListAdapter;
import com.ronypro.android.popularmovies.view.adapter.VideoListAdapter;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.List;


public class MovieDetailFragment
        extends MvpSupportFragment<MovieDetailContract.MovieDetailPresenter>
        implements MovieDetailContract.MovieDetailView,
                   View.OnClickListener,
                   VideoListAdapter.Holder {

    private TextView originalTitleTextView;
    private RatingBar voteAverageRatingBar;
    private TextView releaseDateTextView;
    private TextView synopsisTextView;
    private ImageView posterImageView;
    private VideoListAdapter videoListAdapter;
    private ReviewListAdapter reviewListAdapter;
    private ImageButton favoriteImageButton;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    protected void onInflateLayout() {
        View view = getView();
        originalTitleTextView = (TextView) view.findViewById(R.id.detail_movie_original_title_textview);
        voteAverageRatingBar = (RatingBar) view.findViewById(R.id.detail_movie_vote_average_ratingbar);
        releaseDateTextView = (TextView) view.findViewById(R.id.detail_movie_release_date_textview);
        synopsisTextView = (TextView) view.findViewById(R.id.detail_movie_synopsis_textview);
        posterImageView = (ImageView) view.findViewById(R.id.detail_movie_poster_imageview);
        RecyclerView videosRecyclerView = (RecyclerView) view.findViewById(R.id.detail_movie_videos_list_recycler_view);
        RecyclerView reviewsRecyclerView = (RecyclerView) view.findViewById(R.id.detail_movie_reviews_list_recycler_view);
        favoriteImageButton = (ImageButton) view.findViewById(R.id.detail_movie_favorite_image_button);

        videoListAdapter = new VideoListAdapter(this);
        videosRecyclerView.setAdapter(videoListAdapter);

        reviewListAdapter = new ReviewListAdapter();
        reviewsRecyclerView.setAdapter(reviewListAdapter);

        favoriteImageButton.setOnClickListener(this);
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
        setMovieFavorite(movie.favorite);
    }

    @Override
    public void showPoster(Uri posterUri) {
        Picasso.with(getActivity())
                .load(posterUri)
                .into(posterImageView);
    }

    @Override
    public void setMovieFavorite(boolean favorite) {
        favoriteImageButton.setSelected(favorite);
    }

    @Override
    public void showFavoriteAction() {
        favoriteImageButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showVideoList(List<Video> videoList) {
        videoListAdapter.setEntities(videoList);
    }

    @Override
    public void showReviewList(List<Review> reviewList) {
        reviewListAdapter.setEntities(reviewList);
    }

    @Override
    public void startPlayer(Uri playerUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, playerUri);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.detail_movie_favorite_image_button) {
            getPresenter().onFavoriteMovieClick();
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

    public static void replaceInContainer(FragmentManager fragmentManager, int containerId, Bundle args) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);
        fragmentManager
                .beginTransaction()
                .replace(containerId, movieDetailFragment)
                .commit();
    }

}
