package com.example.application.api.interfaces;

import com.example.application.models.GenresListModel;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenreAPIInterface {
    @GET("3/genre/movie/list")
    Call<GenresListModel> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language

    );
}
