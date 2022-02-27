package com.example.application.models;

import com.google.gson.annotations.SerializedName;

public class PeopleDetailsModel extends PersonModel {

    @SerializedName(value = "deathday")
    private String deathDay;
    @SerializedName(value = "also_known_as")
    private String[] alsoKnownAs;
    private String biography;
    @SerializedName(value = "place_of_birth")
    private String placeOfBirth;
    @SerializedName(value = "imdb_id")
    private String imdbId;
    @SerializedName(value = "homepage")
    private String homePage;



    public String getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(String deathDay) {
        this.deathDay = deathDay;
    }

    public String[] getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(String[] alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
}
