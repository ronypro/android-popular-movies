package com.ronypro.android.popularmovies.presenter.asynctask;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.contract.model.ReviewModel;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;

import java.util.List;

import static com.ronypro.android.mvp.Mvp.getModel;

/**
 * Created by rahony on 08/10/16.
 */

public class ReviewListAsyncTask extends AsyncTask<Movie, Void, ReviewListAsyncTask.Result> {

    private Callback callback;

    ReviewListAsyncTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Result doInBackground(Movie... movies) {
        Movie movie = movies[0];
        Result result = new Result();
        try {
            result.reviewList = getModel(ReviewModel.class).getReviewList(movie);
        } catch (HttpCallException e) {
            result.httpCallException = e;
        } catch (NetworkCallException e) {
            result.networkCallException = e;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (result.reviewList != null) {
            callback.onReviewListResult(result.reviewList);
        } else if (result.httpCallException != null) {
            callback.onReviewListHttpCallException(result.httpCallException);
        } else if (result.networkCallException != null) {
            callback.onReviewListNetworkCallException(result.networkCallException);
        }
    }

    public static void executeParallel(Movie movie, Callback callback) {
        ReviewListAsyncTask movieListAsyncTask = new ReviewListAsyncTask(callback);
        AsyncTaskCompat.executeParallel(movieListAsyncTask, movie);
    }

    static class Result {

        List<Review> reviewList;

        HttpCallException httpCallException;

        NetworkCallException networkCallException;

    }

    public interface Callback {

        void onReviewListResult(List<Review> reviewList);

        void onReviewListHttpCallException(HttpCallException httpCallException);

        void onReviewListNetworkCallException(NetworkCallException networkCallException);
    }
}
