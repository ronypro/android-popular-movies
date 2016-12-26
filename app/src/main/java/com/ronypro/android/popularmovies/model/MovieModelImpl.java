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
import com.ronypro.android.popularmovies.contract.loader.MovieListLoader;
import com.ronypro.android.popularmovies.contract.model.MovieModel;
import com.ronypro.android.popularmovies.contract.model.ReviewModel;
import com.ronypro.android.popularmovies.contract.model.VideoModel;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.MovieList;
import com.ronypro.android.popularmovies.entity.MovieListType;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApi;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApiUtil;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.model.database.MovieValuesHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.model.loader.MovieListLoaderImpl;

import java.util.List;

import retrofit2.Call;

/**
 * Created by rahony on 07/10/16.
 */

public class MovieModelImpl extends AbstractModel implements MovieModel {

    private static final String BASE_POSTER_URI = "http://image.tmdb.org/t/p/";
    private static final String POSTER_THUMBNAIL_SIZE = "w154";
    private static final String POSTER_SIZE = "w780";

    private static final String POPULAR_LIST_TYPE = "popular";
    private static final String TOP_RATED_LIST_TYPE = "top_rated";

    private final MovieDatabaseApi movieDatabaseApi;
    private final ReviewModel reviewModel;
    private final VideoModel videoModel;

    public MovieModelImpl() {
        this.movieDatabaseApi = MovieDatabaseApiUtil.getApiInstance();
        this.reviewModel = Mvp.getModel(ReviewModel.class);
        this.videoModel = Mvp.getModel(VideoModel.class);
    }

    @Override
    public List<Movie> getMovieList(@MovieListType int movieListType) throws HttpCallException, NetworkCallException {
        String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;

        String type = getTypeToApi(movieListType);
        Call<MovieList> movieListCall = movieDatabaseApi.getMovieList(type, apiKey);
        MovieList movieList = MovieDatabaseApiUtil.resolveCall(movieListCall);
        return movieList.results;
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
    public boolean needLoader(Movie movie) {
        return movie.favorite;
    }

    @Override
    public MovieListLoader getMovieListLoader(Context context, MovieListLoader.Callback movieListLoaderCallback) {
        return new MovieListLoaderImpl(context, movieListLoaderCallback);
    }

    @Override
    public boolean needLoaderToList(@MovieListType int movieListType) {
        return movieListType == MovieListType.FAVORITE;
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

    private String getTypeToApi(@MovieListType int movieListType) {
        switch (movieListType) {
            case MovieListType.POPULAR:
                return POPULAR_LIST_TYPE;
            case MovieListType.TOP_RATED:
                return TOP_RATED_LIST_TYPE;
        }
        return null;
    }

}
