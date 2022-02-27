package com.example.application.models;

import java.util.LinkedList;
import java.util.List;

public class MoviesSectionModel {
    private String title;
    private String category;

    public List<MoviesModel.ResultsBean> moviesList = new LinkedList<MoviesModel.ResultsBean>();

    public MoviesSectionModel(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}