package com.ronypro.android.popularmovies.contract.loader;

import com.ronypro.android.database.loader.EntityLoader;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;

import java.util.List;

/**
 * Created by rahony on 12/12/16.
 */

public interface ReviewListLoader extends EntityLoader<Review> {

    interface Callback {

        void onReviewListLoaded(List<Review> reviews);

    }

}
