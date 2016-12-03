package com.ronypro.android.popularmovies.model.provider;

import android.net.Uri;
import android.util.Pair;

import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.provider.TablePathProvider;

/**
 * Created by rahony on 28/11/16.
 */

public class MoviePathProvider extends TablePathProvider {

    private static final String PATH_MOVIE = MoviesContract.PATH_MOVIE;
    private static final String PATH_MOVIE_BY_ID = MoviesContract.PATH_MOVIE + "/#";

    @Override
    protected Pair<String, String> getPathAndType() {
        return new Pair<>(PATH_MOVIE, MoviesContract.MovieEntry.CONTENT_TYPE);
    }

    @Override
    protected Pair<String, String> getItemPathAndType() {
        return new Pair<>(PATH_MOVIE_BY_ID, MoviesContract.MovieEntry.CONTENT_ITEM_TYPE);
    }

    @Override
    protected String getTableName() {
        return MoviesContract.MovieEntry.TABLE_NAME;
    }

    @Override
    public Uri buildInsertedUri(long id) {
        return MoviesContract.MovieEntry.buildMovieUri(id);
    }

}
