package com.ronypro.android.popularmovies.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.ronypro.android.mvp.Mvp;
import com.ronypro.android.mvp.model.AbstractModel;
import com.ronypro.android.popularmovies.BuildConfig;
import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.contract.model.MovieModel;
import com.ronypro.android.popularmovies.contract.model.ReviewModel;
import com.ronypro.android.popularmovies.contract.model.VideoModel;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.MovieList;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApi;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApiUtil;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.model.database.MovieValuesHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;

import java.util.List;

import retrofit2.Call;

/**
 * Created by rahony on 07/10/16.
 */

public class MovieModelImpl extends AbstractModel implements MovieModel {

    public static final String BASE_POSTER_URI = "http://image.tmdb.org/t/p/";
    public static final String POSTER_THUMBNAIL_SIZE = "w154";
    public static final String POSTER_SIZE = "w780";

    private final MovieDatabaseApi movieDatabaseApi;
    private final ReviewModel reviewModel;
    private final VideoModel videoModel;

    public MovieModelImpl() {
        this.movieDatabaseApi = MovieDatabaseApiUtil.getApiInstance();
        this.reviewModel = Mvp.getModel(ReviewModel.class);
        this.videoModel = Mvp.getModel(VideoModel.class);
    }

    @Override
    public List<Movie> getMovieList() throws HttpCallException, NetworkCallException {
        String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;
        String type = getListType();
        Call<MovieList> movieListCall = movieDatabaseApi.getMovieList(type, apiKey);
        MovieList movieList = MovieDatabaseApiUtil.resolveCall(movieListCall);
        return movieList.results;
    }

    private String getListType() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String listTypeKey = context.getString(R.string.pref_movie_list_type_key);
        String listTypeDefault = context.getString(R.string.pref_movie_list_type_value);
        return sharedPreferences.getString(listTypeKey, listTypeDefault);
    }

    @Override
    public Uri getPosterThumbnailUri(Movie movie) {
        return createPosterUri(movie, POSTER_THUMBNAIL_SIZE);
    }

    @Override
    public Uri getPosterUri(Movie movie) {
        return createPosterUri(movie, POSTER_SIZE);
    }

    private Uri createPosterUri(Movie movie, String size) {
        return Uri.parse(BASE_POSTER_URI)
                .buildUpon()
                .appendPath(size)
                .appendEncodedPath(movie.posterPath)
                .build();
    }

    @Override
    public void delete(Movie movie) {
        Uri movieUri = MoviesContract.MovieEntry.buildMovieUri(movie.id);
        getContentResolver().delete(movieUri, null, null);
    }

    @Override
    public void save(Movie movie) {
        saveMovie(movie);
        saveReviewList(movie);
        saveVideoList(movie);
    }

    private void saveMovie(Movie movie) {
        Uri movieUri = MoviesContract.MovieEntry.buildMovieUri(movie.id);
        if (existsMovie(movieUri)) {
            updateMovie(movieUri, movie);
        } else {
            insertMovie(movie);
        }
    }

    private boolean existsMovie(Uri movieUri) {
        Cursor cursor = getContentResolver().query(movieUri, null, null, null, null);
        try {
            return cursor.moveToFirst();
        } finally {
            cursor.close();
        }
    }

    private void updateMovie(Uri movieUri, Movie movie) {
        MovieValuesHelper movieValuesHelper = new MovieValuesHelper();
        ContentValues movieValues = movieValuesHelper.getValuesForUpdate(movie);
        getContentResolver().update(movieUri, movieValues, null, null);
    }

    private void insertMovie(Movie movie) {
        MovieValuesHelper movieValuesHelper = new MovieValuesHelper();
        ContentValues movieValues = movieValuesHelper.getValuesForInsert(movie);
        getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI, movieValues);
    }

    private void saveReviewList(Movie movie) {
        reviewModel.deleteByMovie(movie);
        for (Review review : movie.reviewList) {
            reviewModel.save(review, movie);
        }
    }

    private void saveVideoList(Movie movie) {
        videoModel.deleteByMovie(movie);
        for (Video video : movie.videoList) {
            videoModel.save(video, movie);
        }
    }

}
