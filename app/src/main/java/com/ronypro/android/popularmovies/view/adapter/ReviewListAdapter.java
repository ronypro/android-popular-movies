package com.ronypro.android.popularmovies.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.Review;
import com.ronypro.android.popularmovies.entity.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahony on 10/10/16.
 */

public class ReviewListAdapter extends EntityListAdapter<Review, ReviewListAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.review_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Review review = getEntity(position);
        viewHolder.author.setText(review.author);
        viewHolder.content.setText(review.content);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView author;
        private TextView content;

        ViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.review_author_text_view);
            content = (TextView) itemView.findViewById(R.id.review_content_text_view);
        }

    }

}
