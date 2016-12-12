package com.ronypro.android.popularmovies.presenter;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ronypro.android.mvp.Mvp;
import com.ronypro.android.mvp.presenter.AbstractPresenter;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.contract.MovieDetailContract;
import com.ronypro.android.popularmovies.contract.model.VideoModel;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.contract.model.MovieModel;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.presenter.asynctask.ReviewListAsyncTask;
import com.ronypro.android.popularmovies.presenter.asynctask.VideoListAsyncTask;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */
public class MovieDetailPresenterImpl
        extends AbstractPresenter<MovieDetailContract.MovieDetailView>
        implements MovieDetailContract.MovieDetailPresenter, VideoListAsyncTask.Callback, ReviewListAsyncTask.Callback {

    private static final String TAG = MovieDetailPresenterImpl.class.getSimpleName();

    private static final String SAVED_STATE_MOVIE = "movie";

    private Movie movie;
    private MovieModel movieModel = Mvp.getModel(MovieModel.class);
    private VideoModel videoModel = Mvp.getModel(VideoModel.class);

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(SAVED_STATE_MOVIE);
        }
        if (movie == null) {
            movie = extras.getParcelable(MovieDetailContract.MovieDetailView.EXTRA_MOVIE);
        }
        getView().showMovie(movie);
        Uri posterUri = movieModel.getPosterUri(movie);
        getView().showPoster(posterUri);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (movie != null)
            outState.putParcelable(SAVED_STATE_MOVIE, movie);
    }

    @Override
    public void onStart() {
        super.onStart();
        showOrLoadVideoList();
        showOrLoadReviewList();
    }

    private void showOrLoadVideoList() {
        if (movie.videoList == null)
            loadVideoList();
        else
            showVideoList(movie.videoList);
    }

    private void showOrLoadReviewList() {
        if (movie.reviewList == null)
            loadReviewList();
        else
            showReviewList(movie.reviewList);
    }

    private void loadVideoList() {
        VideoListAsyncTask.executeParallel(movie, this);
    }

    private void loadReviewList() {
        ReviewListAsyncTask.executeParallel(movie, this);
    }

    @Override
    public void onVideoListResult(List<Video> videoList) {
        movie.videoList = videoList;
        showVideoList(videoList);
    }

    private void showVideoList(List<Video> videoList) {
        getView().showVideoList(videoList);
    }

    @Override
    public void onVideoListHttpCallException(HttpCallException httpCallException) {
        Log.e(TAG, "Error " + httpCallException.getCode() + " in request video list", httpCallException);
        getView().showToast(R.string.video_list_load_http_error);
    }

    @Override
    public void onVideoListNetworkCallException(NetworkCallException networkCallException) {
        Log.e(TAG, "Network error in request video list", networkCallException);
        getView().showToast(R.string.network_error);
    }

    @Override
    public void onReviewListResult(List<Review> reviewList) {
        movie.reviewList = reviewList;
        showReviewList(reviewList);
    }

    private void showReviewList(List<Review> reviewList) {
        getView().showReviewList(reviewList);
    }

    @Override
    public void onReviewListHttpCallException(HttpCallException httpCallException) {
        Log.e(TAG, "Error " + httpCallException.getCode() + " in request review list", httpCallException);
        getView().showToast(R.string.review_list_load_http_error);
    }

    @Override
    public void onReviewListNetworkCallException(NetworkCallException networkCallException) {
        Log.e(TAG, "Network error in request review list", networkCallException);
        getView().showToast(R.string.network_error);
    }

    @Override
    public void onFavoriteMovieClick() {
        //TODO: async?
        movieModel.save(movie);
    }

    @Override
    public void onUnfavoriteMovieClick() {
        //TODO: async?
        movieModel.delete(movie);
    }

    @Override
    public void onVideoClick(Video video) {
        Uri playerUri = videoModel.getPlayerUri(video);
        if (playerUri == null) {
            getView().showToast(R.string.video_player_not_supported);
        } else {
            getView().startPlayer(playerUri);
        }
    }

    @Override
    public Uri getThumbnailUri(Video video) {
        return videoModel.getThumbnailUri(video);
    }

}
