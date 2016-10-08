package com.ronypro.android.popularmovies.presenter.asynctask;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.MovieListType;
import com.ronypro.android.popularmovies.model.MovieModel;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;

import java.util.List;

import static com.ronypro.android.mvp.Mvp.getModel;

/**
 * Created by rahony on 08/10/16.
 */

public class MovieListAsyncTask extends AsyncTask<String, Void, MovieListAsyncTask.Result> {

    private Callback callback;

    public MovieListAsyncTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Result doInBackground(String... strings) {
        @MovieListType
        String type = strings[0];
        Result result = new Result();
        try {
            result.movieList = getModel(MovieModel.class).getMovieList(type);
        } catch (HttpCallException e) {
            result.httpCallException = e;
        } catch (NetworkCallException e) {
            result.networkCallException = e;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (result.movieList != null) {
            callback.onMovieListResult(result.movieList);
        } else if (result.httpCallException != null) {
            callback.onMovieListHttpCallException(result.httpCallException);
        } else if (result.networkCallException != null) {
            callback.onMovieListNetworkCallException(result.networkCallException);
        }
    }

    public static void executeParallel(Callback callback, @MovieListType String type) {
        MovieListAsyncTask movieListAsyncTask = new MovieListAsyncTask(callback);
        AsyncTaskCompat.executeParallel(movieListAsyncTask, type);
    }

    static class Result {

        List<Movie> movieList;

        HttpCallException httpCallException;

        NetworkCallException networkCallException;

    }

    public interface Callback {

        void onMovieListResult(List<Movie> movieList);

        void onMovieListHttpCallException(HttpCallException httpCallException);

        void onMovieListNetworkCallException(NetworkCallException networkCallException);
    }
}
