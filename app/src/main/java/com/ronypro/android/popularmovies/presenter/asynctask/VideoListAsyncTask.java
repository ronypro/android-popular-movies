package com.ronypro.android.popularmovies.presenter.asynctask;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.contract.model.VideoModel;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;

import java.util.List;

import static com.ronypro.android.mvp.Mvp.getModel;

/**
 * Created by rahony on 08/10/16.
 */

public class VideoListAsyncTask extends AsyncTask<Movie, Void, VideoListAsyncTask.Result> {

    private Callback callback;

    VideoListAsyncTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Result doInBackground(Movie... movies) {
        Movie movie = movies[0];
        Result result = new Result();
        try {
            result.videoList = getModel(VideoModel.class).getVideoList(movie);
        } catch (HttpCallException e) {
            result.httpCallException = e;
        } catch (NetworkCallException e) {
            result.networkCallException = e;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (result.videoList != null) {
            callback.onVideoListResult(result.videoList);
        } else if (result.httpCallException != null) {
            callback.onVideoListHttpCallException(result.httpCallException);
        } else if (result.networkCallException != null) {
            callback.onVideoListNetworkCallException(result.networkCallException);
        }
    }

    public static void executeParallel(Movie movie, Callback callback) {
        VideoListAsyncTask movieListAsyncTask = new VideoListAsyncTask(callback);
        AsyncTaskCompat.executeParallel(movieListAsyncTask, movie);
    }

    static class Result {

        List<Video> videoList;

        HttpCallException httpCallException;

        NetworkCallException networkCallException;

    }

    public interface Callback {

        void onVideoListResult(List<Video> videoList);

        void onVideoListHttpCallException(HttpCallException httpCallException);

        void onVideoListNetworkCallException(NetworkCallException networkCallException);
    }
}
