package com.example.application.models;

import java.util.List;

public class MovieImagesModel {
    private int id;
    private List<ImageModel> backdrops;
    private List<ImageModel> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ImageModel> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<ImageModel> backdrops) {
        this.backdrops = backdrops;
    }

    public List<ImageModel> getPosters() {
        return posters;
    }

    public void setPosters(List<ImageModel> posters) {
        this.posters = posters;
    }
}
