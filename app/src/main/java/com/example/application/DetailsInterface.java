package com.example.application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DetailsInterface {
    @GET("3/movie/{id}")
    Call<MovieDetails> detailList(
            @Path("id") String ID, //movie ID
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
