package com.ronypro.android.popularmovies.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by rahony on 02/12/16.
 */

public class Video implements Parcelable {

    public Video() {
    }

    @Expose(deserialize = false, serialize = false)
    public long id;

    @Expose
    @SerializedName("id")
    public String idInApi;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("site")
    public String site;

    @Expose
    @SerializedName("key")
    public String key;

    public Video(Parcel in) {
        id = in.readLong();
        idInApi = in.readString();
        name = in.readString();
        site = in.readString();
        key = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(idInApi);
        parcel.writeString(name);
        parcel.writeString(site);
        parcel.writeString(key);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {

        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        public Video[] newArray(int size) {
            return new Video[size];
        }

    };

}
