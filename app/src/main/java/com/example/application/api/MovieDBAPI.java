package com.example.application.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDBAPI {
    public static final String KEY = "b73afcfcb43d313d06045cbc7c409fbc";
    private static final String baseUrl = "https://api.themoviedb.org";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }
        return retrofit;
    }
}
