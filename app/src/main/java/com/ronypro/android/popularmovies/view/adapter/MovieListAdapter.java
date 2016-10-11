package com.ronypro.android.popularmovies.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ronypro.android.popularmovies.R;
import com.ronypro.android.popularmovies.entity.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahony on 10/10/16.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final Holder holder;
    private List<Movie> movies;

    public MovieListAdapter(@NonNull Holder holder) {
        this.holder = holder;
        movies = new ArrayList<>();
    }

    public void setMovies(@NonNull List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movie = getMovie(position);
        Uri posterUri = holder.getPosterUri(movie);
        loadPoster(viewHolder.poster, posterUri);
    }

    private void loadPoster(ImageView imageView, Uri posterUri) {
        Picasso.with(imageView.getContext())
                .load(posterUri)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private Movie getMovie(int position) {
        return movies.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView poster;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            poster = (ImageView) itemView.findViewById(R.id.movie_poster_image_view);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = getMovie(position);
            holder.onMovieClick(movie);
        }
    }

    public interface Holder {

        Uri getPosterUri(Movie movie);

        void onMovieClick(Movie movie);

    }

}
