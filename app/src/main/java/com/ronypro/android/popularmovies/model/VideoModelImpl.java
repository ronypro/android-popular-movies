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

    private static final String SITE_YOUTUBE = "youtube";
    private static final Uri THUMBNAIL_YOUTUBE_URI = Uri.parse("https://img.youtube.com/vi");
    private static final String DEFAULT_THUMBNAIL_YOUTUBE_FILE = "0.jpg";
    private static final Uri PLAYER_YOUTUBE_URI = Uri.parse("https://www.youtube.com/watch");
    private static final String PLAYER_YOUTUBE_VIDEO_PARAMETER = "v";

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

    @Override
    public Uri getThumbnailUri(Video video) {
        if (isFromYouTube(video)) {
            return getYouTubeThumbnailUri(video);
        }
        return null;
    }

    private Uri getYouTubeThumbnailUri(Video video) {
        return THUMBNAIL_YOUTUBE_URI.buildUpon()
                .appendPath(video.key)
                .appendPath(DEFAULT_THUMBNAIL_YOUTUBE_FILE)
                .build();
    }

    @Override
    public Uri getPlayerUri(Video video) {
        if (isFromYouTube(video)) {
            return getYouTubePlayerUri(video);
        }
        return null;
    }

    private Uri getYouTubePlayerUri(Video video) {
        return PLAYER_YOUTUBE_URI.buildUpon()
                .appendQueryParameter(PLAYER_YOUTUBE_VIDEO_PARAMETER, video.key)
                .build();
    }

    private boolean isFromYouTube(Video video) {
        return SITE_YOUTUBE.equalsIgnoreCase(video.site);
    }
}
