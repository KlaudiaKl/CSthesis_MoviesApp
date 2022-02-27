package com.example.application.models;

import com.google.gson.annotations.SerializedName;

public class ImageModel {
    @SerializedName(value = "aspect_ratio")
    private float aspectRatio;
    @SerializedName(value = "file_path")
    private String filePath;
    private int height;
    @SerializedName(value = "iso_639_1")
    private String iso639_1;
    @SerializedName(value = "vote_average")
    private float voteAverage;
    @SerializedName(value = "vote_count")
    private int voteCount;
    private int width;

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIso639_1() {
        return iso639_1;
    }

    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
