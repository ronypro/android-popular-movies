package com.ronypro.android.popularmovies.contract.model;

import android.content.Context;

import com.ronypro.android.mvp.model.Model;
import com.ronypro.android.popularmovies.contract.loader.ReviewListLoader;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.presenter.MovieDetailPresenterImpl;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public interface ReviewModel extends Model {

    List<Review> getReviewList(Movie movie) throws HttpCallException, NetworkCallException;

    void deleteByMovie(Movie movie);

    void save(Review review, Movie movie);

    ReviewListLoader getReviewListLoader(Context context, Movie movie, ReviewListLoader.Callback reviewListLoaderCallback);
}
