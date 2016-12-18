package com.ronypro.android.popularmovies.model.database;

import com.ronypro.android.database.helper.CursorHelper;
import com.ronypro.android.database.helper.EntityCursorHelper;
import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Video;

/**
 * Created by rahony on 12/12/16.
 */

public class VideoCursorHelper extends EntityCursorHelper<Video> {

    private static final String[] PROJECTION = new String[] {
            MoviesContract.VideoEntry._ID,
            MoviesContract.VideoEntry.COLUMN_API_ID,
            MoviesContract.VideoEntry.COLUMN_NAME,
            MoviesContract.VideoEntry.COLUMN_SITE,
            MoviesContract.VideoEntry.COLUMN_SITE_KEY,
    };

    @Override
    public String[] getProjection() {
        return PROJECTION;
    }

    @Override
    protected Video parseToModel(CursorHelper cursorHelper) {
        Video video = new Video();
        video.id = cursorHelper.getLong();
        video.idInApi = cursorHelper.getString();
        video.name = cursorHelper.getString();
        video.site = cursorHelper.getString();
        video.key = cursorHelper.getString();
        return video;
    }

}
