package com.example.application.models;

import java.util.List;

public class GenresListModel {
    public List<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }

    private List<GenreModel> genres;
}
