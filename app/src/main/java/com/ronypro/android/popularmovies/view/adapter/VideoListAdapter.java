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
import com.ronypro.android.popularmovies.entity.Movie;
import com.ronypro.android.popularmovies.entity.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahony on 10/10/16.
 */

public class VideoListAdapter extends EntityListAdapter<Video, VideoListAdapter.ViewHolder> {

    private final Holder holder;

    public VideoListAdapter(@NonNull Holder holder) {
        this.holder = holder;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Video video = getEntity(position);
        viewHolder.name.setText(video.name);
        Uri thumbnailUri = holder.getThumbnailUri(video);
        loadPoster(viewHolder.thumbnail, thumbnailUri);
    }

    private void loadPoster(ImageView imageView, Uri thumbnailUri) {
        Picasso.with(imageView.getContext())
                .load(thumbnailUri)
                .into(imageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private ImageView thumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.video_name_text_view);
            thumbnail = (ImageView) itemView.findViewById(R.id.video_thumbnail_image_view);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Video video = getEntity(position);
            holder.onVideoClick(video);
        }
    }

    public interface Holder {

        Uri getThumbnailUri(Video video);

        void onVideoClick(Video video);
    }

}
