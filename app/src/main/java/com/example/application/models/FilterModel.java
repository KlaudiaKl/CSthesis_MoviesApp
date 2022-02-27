package com.example.application.models;

import java.io.Serializable;

public class FilterModel implements Serializable {
    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getReleaseDateGte() {
        return releaseDateGte;
    }

    public void setReleaseDateGte(String releaseDateGte) {
        this.releaseDateGte = releaseDateGte;
    }

    public String getReleaseDateLte() {
        return releaseDateLte;
    }

    public void setReleaseDateLte(String releaseDateLte) {
        this.releaseDateLte = releaseDateLte;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getWithoutGenres() {
        return withoutGenres;
    }

    public void setWithoutGenres(String withoutGenres) {
        this.withoutGenres = withoutGenres;
    }

    public Integer getRuntimeLte() {
        return runtimeLte;
    }

    public void setRuntimeLte(Integer runtimeLte) {
        this.runtimeLte = runtimeLte;
    }

    public Integer getRuntimeGte() {
        return runtimeGte;
    }

    public void setRuntimeGte(Integer runtimeGte) {
        this.runtimeGte = runtimeGte;
    }

    public Integer getVoteCountGte() {
        return voteCountGte;
    }

    public void setVoteCountGte(Integer voteCountGte) {
        this.voteCountGte = voteCountGte;
    }

    private String sortBy;
    private String releaseDateGte;
    private String releaseDateLte;

    public String getAirDateGte() {
        return airDateGte;
    }

    public void setAirDateGte(String airDateGte) {
        this.airDateGte = airDateGte;
    }

    public String getAirDateLte() {
        return airDateLte;
    }

    public void setAirDateLte(String airDateLte) {
        this.airDateLte = airDateLte;
    }

    private String airDateGte;
    private String airDateLte;
    private String originalLanguage;
    private String withoutGenres;
    private Integer runtimeLte;
    private Integer runtimeGte;
    private Integer voteCountGte = null;
    private String withGenres;
    private String withoutGenresLabel;
    private String withPersonLabel;
    private String withGenresLabel;
    private String withPeople;
    private Integer withReleaseType = null;

    public String getWithPersonLabel() {
        return withPersonLabel;
    }

    public void setWithPersonLabel(String withPersonLabel) {
        this.withPersonLabel = withPersonLabel;
    }

    public String getWithoutGenresLabel() {
        return withoutGenresLabel;
    }

    public void setWithoutGenresLabel(String withoutGenresLabel) {
        this.withoutGenresLabel = withoutGenresLabel;
    }

    public String getWithGenresLabel() {
        return withGenresLabel;
    }

    public void setWithGenresLabel(String withGenresLabel) {
        this.withGenresLabel= withGenresLabel;
    }



    public String getWithGenres() {
        return withGenres;
    }

    public void setWithGenres(String withGenres) {
        this.withGenres = withGenres;
    }

    public String getWithPeople() {
        return withPeople;
    }

    public void setWithPeople(String withPeople) {
        this.withPeople = withPeople;
    }

    public Integer getWithReleaseType() {
        return withReleaseType;
    }

    public void setWithReleaseType(Integer withReleaseType) {
        this.withReleaseType = withReleaseType;
    }


}
