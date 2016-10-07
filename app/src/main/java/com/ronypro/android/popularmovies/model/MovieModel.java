package com.ronypro.android.popularmovies.model;

import com.ronypro.android.mvp.model.Model;
import com.ronypro.android.popularmovies.entity.Movie;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieModel extends Model {

    void getMovieList(MovieListCallback callback);

    interface MovieListCallback {

        void onMovieListSuccess(List<Movie> movieList);

        //TODO: Metodos de erro!!

    }

}
