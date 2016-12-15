package com.ronypro.android.popularmovies.model.database;

import com.ronypro.android.database.helper.CursorHelper;
import com.ronypro.android.database.helper.EntityCursorHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;

/**
 * Created by rahony on 12/12/16.
 */

public class ReviewCursorHelper extends EntityCursorHelper<Review> {

    //TODO: Otimizar o EntityCursorHelper !!
    private static final String[] PROJECTION = new String[] {
            MoviesContract.ReviewEntry._ID,
            MoviesContract.ReviewEntry.COLUMN_API_ID,
            MoviesContract.ReviewEntry.COLUMN_AUTHOR,
            MoviesContract.ReviewEntry.COLUMN_CONTENT,
    };

    @Override
    public String[] getProjection() {
        return PROJECTION;
    }

    @Override
    protected String[] getColumns() {
        return null;
    }

    @Override
    protected Review parseToModel(CursorHelper cursorHelper) {
        Review review = new Review();
        review.id = cursorHelper.getLong();
        review.idInApi = cursorHelper.getString();
        review.author = cursorHelper.getString();
        review.content = cursorHelper.getString();
        return review;
    }

}
