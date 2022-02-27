package com.example.application.api.interfaces;

import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MoviesModel;
import com.example.application.models.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchAPIInterface {
    @GET("3/search/multi")
    Call<SearchModel> search(
            @Query("query") String query,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/search/person")
    Call<SearchModel> searchPerson(
            @Query("query") String query,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}


