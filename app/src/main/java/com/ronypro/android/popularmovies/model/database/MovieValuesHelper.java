package com.ronypro.android.popularmovies.model.database;

import com.ronypro.android.database.helper.ContentValuesHelper;
import com.ronypro.android.database.helper.EntityContentValuesHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.entity.Movie;

/**
 * Created by rahony on 28/11/16.
 */
public class MovieValuesHelper extends EntityContentValuesHelper<Movie> {

    @Override
    protected ContentValuesHelper getValues(Movie movie, @ModelParseOperation int operation) {
        ContentValuesHelper values = new ContentValuesHelper();
        if (operation == INSERT) {
            values.put(MoviesContract.MovieEntry._ID, movie.id);
        }
        values.put(MoviesContract.MovieEntry.COLUMN_POSTER_PATH, movie.posterPath);
        values.put(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE, movie.originalTitle);
        values.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW, movie.overview);
        values.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.voteAverage);
        values.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, movie.releaseDate);
        movie.favorite = true;
        return values;
    }

}
