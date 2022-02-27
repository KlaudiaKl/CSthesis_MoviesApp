package com.example.application.models;

import java.util.List;

public class PeopleCombinedCreditsModel {
    private int id;
    private List<PeopleCombinedCreditsCastModel> cast;
    private List<PeopleCombinedCreditsCrewModel> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PeopleCombinedCreditsCastModel> getCast() {
        return cast;
    }

    public void setCast(List<PeopleCombinedCreditsCastModel> cast) {
        this.cast = cast;
    }

    public List<PeopleCombinedCreditsCrewModel> getCrew() {
        return crew;
    }

    public void setCrew(List<PeopleCombinedCreditsCrewModel> crew) {
        this.crew = crew;
    }
}
