package com.example.application.api.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.application.api.MovieDBAPI;
import com.example.application.api.interfaces.MovieAPIInterface;
import com.example.application.api.interfaces.PeopleAPIInterface;
import com.example.application.api.interfaces.TVSeriesInterface;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.PeopleCombinedCreditsModel;
import com.example.application.models.PeopleDetailsModel;
import com.example.application.models.PeopleImagesModel;
import com.example.application.models.PeopleModel;
import com.example.application.models.TVSeriesModel;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleRepository {

    private static final String TAG = "PeopleRepo";

    private final HashMap<String, MutableLiveData<PeopleModel>> mutableLiveDataDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<PeopleDetailsModel>> peopleDetailsDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<PeopleImagesModel>> peopleImagesDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<PeopleCombinedCreditsModel>> peopleCombinedCreditsDictionary = new HashMap<>();


    @Inject
    public PeopleRepository() {

    }

    public MutableLiveData<PeopleModel> requestPeopleInCategory(String category, int page) {

        if (mutableLiveDataDictionary.containsKey(category + page)) {
            return mutableLiveDataDictionary.get(category + page);
        }

        final MutableLiveData<PeopleModel> mutableLiveData = new MutableLiveData<>();

        PeopleAPIInterface apiService =
                MovieDBAPI.getClient().create(PeopleAPIInterface.class);

        apiService.getPeopleInCategory(category, MovieDBAPI.KEY, "en-US", page).enqueue(new Callback<PeopleModel>() {
            @Override
            public void onResponse(Call<PeopleModel> call, Response<PeopleModel> response) {
                Log.i(TAG, "getPeopleInCategory response=" + response );

                if (response.isSuccessful() && response.body() != null ) {
                    Log.i(TAG, "getPeopleInCategory response.size=" + response.body().getResults().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PeopleModel> call, Throwable t) {
                Log.e(TAG, "getPeopleInCategory onFailure" + call.toString());
            }
        });

        mutableLiveDataDictionary.put(category + page, mutableLiveData);

        return mutableLiveData;
    }

    public LiveData<PeopleDetailsModel> requestPeopleDetails(int peopleId) {
        if (peopleDetailsDictionary.containsKey(peopleId)) {
            return peopleDetailsDictionary.get(peopleId);
        }

        final MutableLiveData<PeopleDetailsModel> mutableLiveData = new MutableLiveData<>();

        PeopleAPIInterface apiService =
                MovieDBAPI.getClient().create(PeopleAPIInterface.class);

        apiService.getPeopleDetails(Integer.toString(peopleId), MovieDBAPI.KEY, "en-US").enqueue(new Callback<PeopleDetailsModel>() {
            @Override
            public void onResponse(Call<PeopleDetailsModel> call, Response<PeopleDetailsModel> response) {
                Log.i(TAG, "getPeopleDetails response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PeopleDetailsModel> call, Throwable t) {
                Log.e(TAG, "getPeopleDetails onFailure" + call.toString());
            }
        });

        peopleDetailsDictionary.put(peopleId, mutableLiveData);

        return mutableLiveData;
    }

    public LiveData<PeopleImagesModel> requestPeopleImages(int personId) {
        if (peopleImagesDictionary.containsKey(personId)) {
            return peopleImagesDictionary.get(personId);
        }

        final MutableLiveData<PeopleImagesModel> mutableLiveData = new MutableLiveData<>();

        PeopleAPIInterface apiService =
                MovieDBAPI.getClient().create(PeopleAPIInterface.class);

        apiService.getPeopleImages(Integer.toString(personId), MovieDBAPI.KEY).enqueue(new Callback<PeopleImagesModel>() {
            @Override
            public void onResponse(Call<PeopleImagesModel> call, Response<PeopleImagesModel> response) {
                Log.i(TAG, "getPeopleImages response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PeopleImagesModel> call, Throwable t) {
                Log.e(TAG, "getPeopleImages onFailure" + call.toString());
            }
        });

        peopleImagesDictionary.put(personId, mutableLiveData);

        return mutableLiveData;
    }

    public LiveData<PeopleCombinedCreditsModel> requestPeopleCombinedCredits(int personId) {
        if (peopleCombinedCreditsDictionary.containsKey(personId)) {
            return peopleCombinedCreditsDictionary.get(personId);
        }

        final MutableLiveData<PeopleCombinedCreditsModel> mutableLiveData = new MutableLiveData<>();

        PeopleAPIInterface apiService =
                MovieDBAPI.getClient().create(PeopleAPIInterface.class);

        apiService.getPeopleCombinedCredits(Integer.toString(personId), MovieDBAPI.KEY).enqueue(new RepositoryLiveDataCallback<>(mutableLiveData, TAG, "getPeopleCombinedCredits"));

        peopleCombinedCreditsDictionary.put(personId, mutableLiveData);

        return mutableLiveData;
    }
}
