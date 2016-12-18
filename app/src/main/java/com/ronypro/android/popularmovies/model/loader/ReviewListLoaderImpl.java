package com.ronypro.android.popularmovies.model.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.ronypro.android.database.helper.EntityCursorHelper;
import com.ronypro.android.database.loader.BaseEntityLoader;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.contract.loader.ReviewListLoader;
import com.ronypro.android.popularmovies.contract.loader.VideoListLoader;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.model.database.ReviewCursorHelper;
import com.ronypro.android.popularmovies.model.database.VideoCursorHelper;

/**
 * Created by rahony on 12/12/16.
 */

public class ReviewListLoaderImpl
        extends BaseEntityLoader<Review>
        implements ReviewListLoader {

    private final Movie movie;
    private final Callback callback;

    public ReviewListLoaderImpl(Context context, Movie movie, Callback callback) {
        super(context, new ReviewCursorHelper());
        this.movie = movie;
        this.callback = callback;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MoviesContract.ReviewEntry.buildReviewByMovieUri(movie.id), getProjectionFromHelper(), null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, EntityCursorHelper<Review> data) {
        callback.onReviewListLoaded(data.getAllEntities());
    }

}
