package com.ronypro.android.popularmovies.contract.model;

import android.net.Uri;

import com.ronypro.android.mvp.model.Model;
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.ronypro.android.popularmovies.model.client.HttpCallException;
import com.ronypro.android.popularmovies.model.client.NetworkCallException;

import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public interface VideoModel extends Model {

    List<Video> getVideoList(Movie movie) throws HttpCallException, NetworkCallException;

    void deleteByMovie(Movie movie);

    void save(Video video, Movie movie);

    Uri getThumbnailUri(Video video);

    Uri getPlayerUri(Video video);
}
