package com.ronypro.android.popularmovies.model.database;

import com.ronypro.android.database.helper.ContentValuesHelper;
import com.ronypro.android.database.helper.EntityContentValuesHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;

/**
 * Created by rahony on 28/11/16.
 */
public class VideoValuesHelper extends EntityContentValuesHelper<Video> {

    private final Movie movie;

    public VideoValuesHelper(Movie movie) {
        this.movie = movie;
    }

    @Override
    protected ContentValuesHelper getValues(Video video, @ModelParseOperation int operation) {
        ContentValuesHelper values = new ContentValuesHelper();
        values.put(MoviesContract.VideoEntry.COLUMN_MOVIE_ID, movie.id);
        values.put(MoviesContract.VideoEntry.COLUMN_API_ID, video.idInApi);
        values.put(MoviesContract.VideoEntry.COLUMN_NAME, video.name);
        values.put(MoviesContract.VideoEntry.COLUMN_SITE, video.site);
        values.put(MoviesContract.VideoEntry.COLUMN_SITE_KEY, video.key);
        return values;
    }

}
