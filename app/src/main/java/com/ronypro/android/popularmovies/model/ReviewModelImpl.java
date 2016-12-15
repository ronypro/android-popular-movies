package com.ronypro.android.popularmovies.model;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.ronypro.android.mvp.model.AbstractModel;
import com.ronypro.android.popularmovies.BuildConfig;
import com.ronypro.android.popularmovies.contract.loader.ReviewListLoader;
import com.ronypro.android.popularmovies.contract.model.ReviewModel;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.ReviewList;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApi;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApiUtil;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.model.database.ReviewValuesHelper;
import com.ronypro.android.popularmovies.model.loader.ReviewListLoaderImpl;
import com.ronypro.android.popularmovies.util.UriUtil;

import java.util.List;

import retrofit2.Call;

/**
 * Created by rahony on 07/10/16.
 */

public class ReviewModelImpl extends AbstractModel implements ReviewModel {

    private MovieDatabaseApi movieDatabaseApi;

    public ReviewModelImpl() {
        this.movieDatabaseApi = MovieDatabaseApiUtil.getApiInstance();
    }

    @Override
    public List<Review> getReviewList(Movie movie) throws HttpCallException, NetworkCallException {
        String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;
        Call<ReviewList> reviewListCall = movieDatabaseApi.getReviewList(movie.id, apiKey);
        ReviewList reviewList = MovieDatabaseApiUtil.resolveCall(reviewListCall);
        return reviewList.results;
    }

    @Override
    public void deleteByMovie(Movie movie) {
        Uri reviewByMovieUri = MoviesContract.ReviewEntry.buildReviewByMovieUri(movie.id);
        getContentResolver().delete(reviewByMovieUri, null, null);
    }

    @Override
    public void save(Review review, Movie movie) {
        ReviewValuesHelper reviewValuesHelper = new ReviewValuesHelper(movie);
        ContentValues reviewValues = reviewValuesHelper.getValuesForInsert(review);
        Uri reviewUri = getContentResolver().insert(MoviesContract.ReviewEntry.CONTENT_URI, reviewValues);
        review.id = UriUtil.getLongFromUri(reviewUri);
    }

    @Override
    public ReviewListLoader getReviewListLoader(Context context, Movie movie, ReviewListLoader.Callback reviewListLoaderCallback) {
        return new ReviewListLoaderImpl(context, movie, reviewListLoaderCallback);
    }
}
