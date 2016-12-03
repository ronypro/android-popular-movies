package com.ronypro.android.popularmovies.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ronypro.android.popularmovies.util.ParcilUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by rahony on 07/10/16.
 */

public class Movie implements Parcelable {

    public Movie() {
    }

    @Expose
    @SerializedName("id")
    public long id;

    @Expose
    @SerializedName("poster_path")
    public String posterPath;

    @Expose
    @SerializedName("original_title")
    public String originalTitle;

    @Expose
    @SerializedName("overview")
    public String overview;

    @Expose
    @SerializedName("vote_average")
    public float voteAverage;

    @Expose
    @SerializedName("release_date")
    public Date releaseDate;

    @Expose(deserialize = false, serialize = false)
    public List<Video> videoList;

    @Expose(deserialize = false, serialize = false)
    public List<Review> reviewList;

    public Movie(Parcel in) {
        id = in.readLong();
        posterPath = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        voteAverage = in.readFloat();
        long releaseDateMs = in.readLong();
        if (releaseDateMs != Long.MIN_VALUE)
            releaseDate = new Date(releaseDateMs);
        videoList = ParcilUtil.readTypedListOrNUll(in, Video.CREATOR);
        reviewList = ParcilUtil.readTypedListOrNUll(in, Review.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(posterPath);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeFloat(voteAverage);
        long releaseDateMs = releaseDate != null? releaseDate.getTime() : Long.MIN_VALUE;
        parcel.writeLong(releaseDateMs);
        parcel.writeTypedList(videoList);
        parcel.writeTypedList(reviewList);
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
