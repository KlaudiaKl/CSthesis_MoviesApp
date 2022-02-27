package com.example.application.models;

import com.google.gson.annotations.SerializedName;

public class SearchModelItem {
    public static final String MEDIA_TYPE_MOVIE = "movie";
    public static final String MEDIA_TYPE_TV = "tv";
    public static final String MEDIA_TYPE_PERSON = "person";
    private int id;
    @SerializedName(value = "media_type")
    private String mediaType;
    @SerializedName(value = "poster_path")
    private String posterPath;
    @SerializedName(value = "profile_path")
    private String profilePath;
    private String name;
    private String title;

    public String getName() {
        return name != null ? name : title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getImagePath() {
        return posterPath != null ? posterPath : profilePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
