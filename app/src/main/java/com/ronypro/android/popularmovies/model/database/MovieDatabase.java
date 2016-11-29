package com.ronypro.android.popularmovies.model.database;

import com.ronypro.android.database.DatabaseInfo;

/**
 * Created by rahony on 28/11/16.
 */

public class MovieDatabase extends DatabaseInfo {

    private static final String NAME = "movies";
    private static final int VERSION = 1;

    public MovieDatabase() {
        super(NAME, VERSION);
    }

}
