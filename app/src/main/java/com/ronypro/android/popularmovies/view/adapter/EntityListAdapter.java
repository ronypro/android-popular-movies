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
import com.ronypro.android.popularmovies.entity.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahony on 10/10/16.
 */

public abstract class EntityListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> entities;

    public void setEntities(@NonNull List<T> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return entities == null? 0 : entities.size();
    }

    public T getEntity(int position) {
        return entities.get(position);
    }

}
