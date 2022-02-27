package com.example.application.api.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.application.api.MovieDBAPI;
import com.example.application.api.interfaces.SearchAPIInterface;
import com.example.application.models.SearchModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private static final String TAG = "Search";

    @Inject
    public SearchRepository() {

    }

    public MutableLiveData<SearchModel> requestSearch(String query, int page) {

        final MutableLiveData<SearchModel> mutableLiveData = new MutableLiveData<>();

        SearchAPIInterface apiService =
                MovieDBAPI.getClient().create(SearchAPIInterface.class);

        apiService.search(query, MovieDBAPI.KEY, "en-US", 1).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                Log.i(TAG, "Search response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "Search response.size=" + response.body().getResults().size());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Log.e(TAG, "Search onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
    public MutableLiveData<SearchModel> requestSearchPerson(String query, int page) {

        final MutableLiveData<SearchModel> mutableLiveData = new MutableLiveData<>();

        SearchAPIInterface apiService =
                MovieDBAPI.getClient().create(SearchAPIInterface.class);

        apiService.searchPerson(query, MovieDBAPI.KEY, "en-US", 1).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                Log.i(TAG, "Search response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "Search response.size=" + response.body().getResults().size());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Log.e(TAG, "Search onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
}
