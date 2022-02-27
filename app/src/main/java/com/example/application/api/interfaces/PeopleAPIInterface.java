package com.example.application.api.interfaces;

import com.example.application.models.MovieImagesModel;
import com.example.application.models.PeopleCombinedCreditsModel;
import com.example.application.models.PeopleDetailsModel;
import com.example.application.models.PeopleImagesModel;
import com.example.application.models.PeopleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeopleAPIInterface {
    @GET("3/person/{category}")
    Call<PeopleModel> getPeopleInCategory(
            @Path("category") String category, //movie ID
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("3/person/{person_id}")
    Call<PeopleDetailsModel> getPeopleDetails(
        @Path("person_id") String personId,
        @Query("api_key") String apiKey,
        @Query("language") String language
    );

    @GET("3/person/{person_id}/images")
    Call<PeopleImagesModel> getPeopleImages(
            @Path("person_id") String personId,
            @Query("api_key") String apiKey
    );

    @GET("3/person/{person_id}/combined_credits")
    Call<PeopleCombinedCreditsModel> getPeopleCombinedCredits(
            @Path("person_id") String personId,
            @Query("api_key") String apiKey
    );
}
