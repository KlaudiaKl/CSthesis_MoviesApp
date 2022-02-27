package com.example.application.models;

import java.util.List;

public class PeopleImagesModel {
    private int id;
    private List<ImageModel> profiles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ImageModel> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ImageModel> profiles) {
        this.profiles = profiles;
    }
}
