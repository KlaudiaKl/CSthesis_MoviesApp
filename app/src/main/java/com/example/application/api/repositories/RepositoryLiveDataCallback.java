package com.example.application.api.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryLiveDataCallback<T> implements Callback<T> {

    private final MutableLiveData<T> liveData;
    private final String tag;
    private final String methodName;

    public RepositoryLiveDataCallback(MutableLiveData<T> liveData, String tag, String methodName) {
        this.liveData = liveData;
        this.tag = tag;
        this.methodName = methodName;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.i(tag, methodName + " response=" + response);

        if (response.isSuccessful() && response.body() != null) {
            liveData.setValue(response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(tag, methodName + " onFailure" + call.toString(), t);
    }
}
