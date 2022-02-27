package com.example.application.models;

import com.google.gson.annotations.SerializedName;

public class MovieCreditsCastModel extends PersonModel {
    @SerializedName("cast_id")
    private int castId;
    private String character;
    @SerializedName("credit_id")
    private String creditId;
    private int order;

    public int getCastId() {
        return castId;
    }

    public void setCastId(int castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
