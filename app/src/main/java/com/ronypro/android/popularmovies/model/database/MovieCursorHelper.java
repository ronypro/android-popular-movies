package com.ronypro.android.popularmovies.model.database;

import com.ronypro.android.database.helper.CursorHelper;
import com.ronypro.android.database.helper.EntityCursorHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.entity.Movie;

/**
 * Created by rahony on 12/12/16.
 */

public class MovieCursorHelper extends EntityCursorHelper<Movie> {

    private static final String[] PROJECTION = new String[] {
            MoviesContract.MovieEntry._ID,
            MoviesContract.MovieEntry.COLUMN_POSTER_PATH,
            MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE,
            MoviesContract.MovieEntry.COLUMN_OVERVIEW,
            MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE,
            MoviesContract.MovieEntry.COLUMN_RELEASE_DATE
    };

    @Override
    public String[] getProjection() {
        return PROJECTION;
    }

    @Override
    protected Movie parseToModel(CursorHelper cursorHelper) {
        Movie movie = new Movie();
        movie.id = cursorHelper.getLong();
        movie.posterPath = cursorHelper.getString();
        movie.originalTitle = cursorHelper.getString();
        movie.overview = cursorHelper.getString();
        movie.voteAverage = cursorHelper.getFloat();
        movie.releaseDate = cursorHelper.getDate();
        movie.favorite = true;
        return movie;
    }

}
