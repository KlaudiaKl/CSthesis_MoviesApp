package com.example.application.models;

import java.util.List;

public class MovieCreditsModel {
    private int id;
    private List<MovieCreditsCastModel> cast;
    private List<MovieCreditsCrewModel> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieCreditsCastModel> getCast() {
        return cast;
    }

    public void setCast(List<MovieCreditsCastModel> cast) {
        this.cast = cast;
    }

    public List<MovieCreditsCrewModel> getCrew() {
        return crew;
    }

    public void setCrew(List<MovieCreditsCrewModel> crew) {
        this.crew = crew;
    }
}
