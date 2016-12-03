package com.ronypro.android.popularmovies.model;

import android.content.ContentValues;
import android.net.Uri;

import com.ronypro.android.mvp.model.AbstractModel;
import com.ronypro.android.popularmovies.BuildConfig;
import com.ronypro.android.popularmovies.contract.model.VideoModel;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.entity.VideoList;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApi;
import com.ronypro.android.popularmovies.model.client.MovieDatabaseApiUtil;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.model.database.VideoValuesHelper;
import com.ronypro.android.popularmovies.util.UriUtil;

import java.util.List;

import retrofit2.Call;

/**
 * Created by rahony on 07/10/16.
 */

public class VideoModelImpl extends AbstractModel implements VideoModel {

    private MovieDatabaseApi movieDatabaseApi;

    public VideoModelImpl() {
        this.movieDatabaseApi = MovieDatabaseApiUtil.getApiInstance();
    }

    @Override
    public List<Video> getVideoList(Movie movie) throws HttpCallException, NetworkCallException {
        String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;
        Call<VideoList> videoListCall = movieDatabaseApi.getVideoList(movie.id, apiKey);
        VideoList videoList = MovieDatabaseApiUtil.resolveCall(videoListCall);
        return videoList.results;
    }

    @Override
    public void deleteByMovie(Movie movie) {
        Uri videoByMovieUri = MoviesContract.VideoEntry.buildVideoByMovieUri(movie.id);
        getContentResolver().delete(videoByMovieUri, null, null);
    }

    @Override
    public void save(Video video, Movie movie) {
        VideoValuesHelper videoValuesHelper = new VideoValuesHelper(movie);
        ContentValues reviewValues = videoValuesHelper.getValuesForInsert(video);
        Uri videoUri = getContentResolver().insert(MoviesContract.VideoEntry.CONTENT_URI, reviewValues);
        video.id = UriUtil.getLongFromUri(videoUri);
    }

}
