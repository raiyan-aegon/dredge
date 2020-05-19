package com.mukhtarinc.dredge.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "top_movies")
public class TopRatedMovie implements Parcelable {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "id")
    @SerializedName("id")
    private String movie_id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String movie_title;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String poster_path;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private String vote_average;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String release_date;


    public TopRatedMovie(){}

    protected TopRatedMovie(Parcel in) {
        movie_id = in.readString();
        movie_title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        vote_average = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<TopRatedMovie> CREATOR = new Creator<TopRatedMovie>() {
        @Override
        public TopRatedMovie createFromParcel(Parcel in) {
            return new TopRatedMovie(in);
        }

        @Override
        public TopRatedMovie[] newArray(int size) {
            return new TopRatedMovie[size];
        }
    };

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movie_id);
        parcel.writeString(movie_title);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(vote_average);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }
}

