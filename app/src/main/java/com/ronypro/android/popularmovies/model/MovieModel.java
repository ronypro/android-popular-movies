package com.ronypro.android.popularmovies.model;

import android.net.Uri;

import com.ronypro.android.mvp.model.Model;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public interface MovieModel extends Model {

    List<Movie> getMovieList() throws HttpCallException, NetworkCallException;

    Uri getPosterUri(Movie movie);

}
