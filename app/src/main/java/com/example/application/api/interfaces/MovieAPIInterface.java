package com.example.application.api.interfaces;

import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPIInterface {
    @GET("3/movie/{category}")
    Call<MoviesModel> getMoviesInCategory(
            @Path("category") String category, //or movie ID
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/movie/{id}/similar")
    Call<MoviesModel> getSimilarMovies(
            @Path("id") String id, //or movie ID
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/movie/{id}/recommendations")
    Call<MoviesModel> getRecommendedMovies(
            @Path("id") String id, //or movie ID
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/movie/{id}/credits")
    Call<MovieCreditsModel> getMovieCredits(
            @Path("id") String id,
            @Query("api_key") String apiKey
    );

    @GET("3/movie/{id}")
    Call<MovieDetailsModel> getMovieDetails(
            @Path("id") String id, //movie ID
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("3/movie/{id}/images")
    Call<MovieImagesModel> getMovieImages(
            @Path("id") String id, //movie ID
            @Query("api_key") String apiKey
    );

    @GET("3/discover/movie")
    Call<MoviesModel> getDiscoverMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("year") Integer year, // Integer, bo optional - jak nie wymagany to null
            @Query("with_genres") String withGenres, // Join string array with , using String.join
            @Query("with_release_type") Integer withReleaseType,
            @Query("release_date.gte") String releaseDateGte,
            @Query("release_date.lte") String releaseDateLte,
            @Query("vote_count.gte")Integer voteCountGte,
            @Query("sort_by") String sortBy,
            @Query("with_people") String withPeople,
            @Query("without_genres") String withoutGenres,
            @Query("with_runtime.gte") Integer runtimeGte,
            @Query("with_runtime.lte") Integer runtimeLte,
            @Query("with_original_language") String originalLanguage);

}
