package com.ronypro.android.popularmovies.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by rahony on 07/10/16.
 */

public class Movie implements Parcelable {

    public Movie() {
    }

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("overview")
    public String overview;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("release_date")
    public Date releaseDate;

    public Movie(Parcel in) {
        posterPath = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        voteAverage = in.readFloat();
        long releaseDateMs = in.readLong();
        if (releaseDateMs != Long.MIN_VALUE)
            releaseDate = new Date(releaseDateMs);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(posterPath);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeFloat(voteAverage);
        long releaseDateMs = releaseDate != null? releaseDate.getTime() : Long.MIN_VALUE;
        parcel.writeLong(releaseDateMs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }

    };

}
