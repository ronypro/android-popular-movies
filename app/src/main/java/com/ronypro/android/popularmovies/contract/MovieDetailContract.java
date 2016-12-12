package com.ronypro.android.popularmovies.contract;

import android.net.Uri;

import com.ronypro.android.mvp.presenter.Presenter;
import com.ronypro.android.mvp.view.View;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieDetailContract {

    interface MovieDetailView extends View<MovieDetailPresenter> {

        String EXTRA_MOVIE = MovieDetailView.class.getName() + ".movie";

        void showMovie(Movie movie);

        void showPoster(Uri posterUri);

        void showVideoList(List<Video> videoList);

        void showReviewList(List<Review> reviewList);

        void startPlayer(Uri playerUri);

    }

    interface MovieDetailPresenter extends Presenter<MovieDetailView> {

        void onFavoriteMovieClick();

        void onUnfavoriteMovieClick();

        void onVideoClick(Video video);

        Uri getThumbnailUri(Video video);
    }


}
