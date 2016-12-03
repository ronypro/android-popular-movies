package com.ronypro.android.popularmovies.model.database;

import com.ronypro.android.database.helper.ContentValuesHelper;
import com.ronypro.android.database.helper.EntityContentValuesHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;

/**
 * Created by rahony on 28/11/16.
 */
public class ReviewValuesHelper extends EntityContentValuesHelper<Review> {

    private final Movie movie;

    public ReviewValuesHelper(Movie movie) {
        this.movie = movie;
    }

    @Override
    protected ContentValuesHelper getValues(Review review, @ModelParseOperation int operation) {
        ContentValuesHelper values = new ContentValuesHelper();
        values.put(MoviesContract.ReviewEntry.COLUMN_MOVIE_ID, movie.id);
        values.put(MoviesContract.ReviewEntry.COLUMN_API_ID, review.idInApi);
        values.put(MoviesContract.ReviewEntry.COLUMN_AUTHOR, review.author);
        values.put(MoviesContract.ReviewEntry.COLUMN_CONTENT, review.content);
        return values;
    }

}
