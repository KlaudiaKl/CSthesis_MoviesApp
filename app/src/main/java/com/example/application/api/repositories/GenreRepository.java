package com.example.application.api.repositories;

import android.util.Log;

import com.example.application.api.MovieDBAPI;
import com.example.application.api.interfaces.GenreAPIInterface;
import com.example.application.api.interfaces.MovieAPIInterface;
import com.example.application.models.GenresListModel;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;

import java.util.HashMap;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRepository {
    private static final String TAG = "GenreRepo";

    private MutableLiveData<GenresListModel> mutableLiveData = null;


    @Inject
    public GenreRepository() {

    }

    public MutableLiveData<GenresListModel> requestGenres() {

        if (mutableLiveData!=null) {
            return mutableLiveData;
        }

        final MutableLiveData<GenresListModel> mutableLiveData = new MutableLiveData<>();

        GenreAPIInterface apiService =
                MovieDBAPI.getClient().create(GenreAPIInterface.class);

        apiService.getGenres(MovieDBAPI.KEY, "en-US").enqueue(new Callback<GenresListModel>() {
            @Override
            public void onResponse(Call<GenresListModel> call, Response<GenresListModel> response) {
                Log.i(TAG, "getGenres response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "getGenres response.size=" + response.body().getGenres().size());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenresListModel> call, Throwable t) {
                Log.e(TAG, "getGenres onFailure" + call.toString());
            }
        });

        this.mutableLiveData=mutableLiveData;

        return mutableLiveData;
    }

}
