package com.ronypro.android.popularmovies.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rahony on 02/12/16.
 */

public class Review implements Parcelable {

    public Review() {
    }

    @Expose(deserialize = false, serialize = false)
    public long id;

    @Expose
    @SerializedName("id")
    public String idInApi;

    @Expose
    @SerializedName("author")
    public String author;

    @Expose
    @SerializedName("content")
    public String content;

    public Review(Parcel in) {
        id = in.readLong();
        idInApi = in.readString();
        author = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(idInApi);
        parcel.writeString(author);
        parcel.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {

        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        public Review[] newArray(int size) {
            return new Review[size];
        }

    };

}
