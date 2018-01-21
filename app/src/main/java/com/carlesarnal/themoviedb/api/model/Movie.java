package com.carlesarnal.themoviedb.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by carles on 17/01/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "poster_path",
        "overview",
        "id",
        "title",
        "backdrop_path",
        "popularity",
        "vote_count",
        "vote_average"
})
public class Movie implements Parcelable {

    @JsonProperty("poster_path")
    public String posterPath;
    @JsonProperty("overview")
    public String overview;
    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("backdrop_path")
    public String backdropPath;
    @JsonProperty("popularity")
    public float popularity;
    @JsonProperty("vote_count")
    public int voteCount;
    @JsonProperty("vote_average")
    public float voteAverage;

    protected Movie(Parcel in) {
        posterPath = in.readString();
        overview = in.readString();
        id = in.readString();
        title = in.readString();
        backdropPath = in.readString();
        popularity = in.readFloat();
        voteCount = in.readInt();
        voteAverage = in.readFloat();
    }

    public Movie () {

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeFloat(popularity);
        dest.writeInt(voteCount);
        dest.writeFloat(voteAverage);
    }
}
