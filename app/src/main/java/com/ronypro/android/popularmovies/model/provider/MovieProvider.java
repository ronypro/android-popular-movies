package com.ronypro.android.popularmovies.model.provider;

import android.database.sqlite.SQLiteOpenHelper;

import com.ronypro.android.database.DatabaseHelper;
import com.ronypro.android.popularmovies.model.database.MovieDatabase;
import com.ronypro.android.popularmovies.model.database.MoviesContract;
import com.ronypro.android.provider.CentralProvider;

/**
 * Created by rahony on 28/11/16.
 */

public class MovieProvider extends CentralProvider {

    public MovieProvider() {
        super(MoviesContract.CONTENT_AUTHORITY);
    }

    @Override
    protected SQLiteOpenHelper createDatabaseOpenHelper() {
        return new DatabaseHelper(getContext(), new MovieDatabase());
    }

    @Override
    protected void registerPaths() {
        registerPath(new MoviePathProvider());
    }

}
