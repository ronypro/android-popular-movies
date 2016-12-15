package com.ronypro.android.popularmovies.model.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.ronypro.android.database.helper.EntityCursorHelper;
import com.ronypro.android.database.loader.BaseEntityLoader;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.contract.loader.MovieListLoader;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.model.database.MovieCursorHelper;

/**
 * Created by rahony on 12/12/16.
 */

public class MovieListLoaderImpl
        extends BaseEntityLoader<Movie>
        implements MovieListLoader {

    private final MovieListLoader.Callback callback;

    public MovieListLoaderImpl(Context context, Callback callback) {
        super(context, new MovieCursorHelper());
        this.callback = callback;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MoviesContract.MovieEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, EntityCursorHelper<Movie> data) {
        callback.onMovieListLoaded(data.getAllEntities());
    }

}
