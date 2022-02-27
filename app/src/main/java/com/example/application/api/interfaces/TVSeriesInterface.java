package com.example.application.api.interfaces;

import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.TVSeriesDetailsModel;
import com.example.application.models.TVSeriesLatestModel;
import com.example.application.models.TVSeriesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVSeriesInterface {
    @GET("3/tv/{category}")
    Call<TVSeriesModel> getTVSeriesInCategory(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/tv/latest")
    Call<TVSeriesLatestModel> getLatest(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("3/tv/{tv_id}")
    Call<TVSeriesDetailsModel> getTVSeriesDetails(
            @Path("tv_id") String id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("3/tv/{tv_id}/images")
    Call<MovieImagesModel> getTVSeriesImages(
            @Path("tv_id") String id,
            @Query("api_key") String apiKey
    );

    @GET("3/tv/{id}/credits")
    Call<MovieCreditsModel> getTVSeriesCredits(
            @Path("id") String id,
            @Query("api_key") String apiKey
    );

    @GET("3/tv/{id}/recommendations")
    Call<TVSeriesModel> getRecommendedTVSeries(
            @Path("id") String id,
            @Query("api_key") String apiKey
    );

    @GET("3/tv/{id}/similar")
    Call<TVSeriesModel> getSimilarTVSeries(
            @Path("id") String id,
            @Query("api_key") String apiKey
    );

    @GET("3/discover/tv")
    Call<TVSeriesModel> getDiscoverTV(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("year") Integer year, // Integer, bo optional - jak nie wymagany to null
            @Query("with_genres") String withGenres, // Join string array with , using String.join
            @Query("air_date.gte") String airDateGte,
            @Query("air_date.lte") String airDateLte,
            @Query("vote_count.gte")Integer voteCountGte,
            @Query("sort_by") String sortBy,
           // @Query("with_people") String withPeople,
            @Query("without_genres") String withoutGenres,
            @Query("with_runtime.gte") Integer runtimeGte,
            @Query("with_runtime.lte") Integer runtimeLte,
            @Query("with_original_language") String originalLanguage);


}
