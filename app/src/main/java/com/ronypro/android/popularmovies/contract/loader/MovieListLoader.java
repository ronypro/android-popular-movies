package com.ronypro.android.popularmovies.contract.loader;

import com.ronypro.android.database.loader.EntityLoader;
import com.ronypro.android.popularmovies.entity.Movie;

import java.util.List;

/**
 * Created by rahony on 12/12/16.
 */

public interface MovieListLoader extends EntityLoader<Movie> {

    interface Callback {

        void onMovieListLoaded(List<Movie> movies);

    }

}
