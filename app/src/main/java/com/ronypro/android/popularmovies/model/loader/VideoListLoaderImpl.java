package com.ronypro.android.popularmovies.model.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.ronypro.android.database.helper.EntityCursorHelper;
import com.ronypro.android.database.loader.BaseEntityLoader;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.contract.loader.VideoListLoader;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.model.database.MovieCursorHelper;
import com.ronypro.android.popularmovies.model.database.VideoCursorHelper;

/**
 * Created by rahony on 12/12/16.
 */

public class VideoListLoaderImpl
        extends BaseEntityLoader<Video>
        implements VideoListLoader {

    private final Movie movie;
    private final Callback callback;

    public VideoListLoaderImpl(Context context, Movie movie, Callback callback) {
        super(context, new VideoCursorHelper());
        this.movie = movie;
        this.callback = callback;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MoviesContract.VideoEntry.buildVideoByMovieUri(movie.id), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, EntityCursorHelper<Video> data) {
        callback.onVideoListLoaded(data.getAllEntities());
    }

}
