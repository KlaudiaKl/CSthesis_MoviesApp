package com.example.application.api.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.application.api.MovieDBAPI;
import com.example.application.api.interfaces.MovieAPIInterface;
import com.example.application.api.interfaces.TVSeriesInterface;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;
import com.example.application.models.TVSeriesDetailsModel;
import com.example.application.models.TVSeriesModel;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVSeriesRepository {

    private static final String TAG = "TVSeriesRepo";

    private final HashMap<String, MutableLiveData<TVSeriesModel>> mutableLiveDataDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<TVSeriesDetailsModel>> tvSeriesDetailsDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<MovieImagesModel>> tvSeriesImagesDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<MovieCreditsModel>> tvSeriesCreditsDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<TVSeriesModel>> recommendedDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<TVSeriesModel>> similarDictionary = new HashMap<>();
    @Inject
    public TVSeriesRepository() {

    }

    public MutableLiveData<TVSeriesModel> requestTVSeriesInCategory(String category, int page) {

        if (mutableLiveDataDictionary.containsKey(category + page)) {
            return mutableLiveDataDictionary.get(category + page);
        }

        final MutableLiveData<TVSeriesModel> mutableLiveData = new MutableLiveData<>();

        TVSeriesInterface apiService =
                MovieDBAPI.getClient().create(TVSeriesInterface.class);

        apiService.getTVSeriesInCategory(category, MovieDBAPI.KEY, "en-US", page).enqueue(new Callback<TVSeriesModel>() {
            @Override
            public void onResponse(Call<TVSeriesModel> call, Response<TVSeriesModel> response) {
                Log.i(TAG, "getTVSeriesInCategory response=" + response );

                if (response.isSuccessful() && response.body() != null ) {
                    Log.i(TAG, "getTVSeriesInCategory response.size=" + response.body().getResults().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TVSeriesModel> call, Throwable t) {
                Log.e(TAG, "getTVSeriesInCategory onFailure" + call.toString());
            }
        });

        mutableLiveDataDictionary.put(category + page, mutableLiveData);

        return mutableLiveData;
    }

    public LiveData<TVSeriesDetailsModel> requestTVSeriesDetails(int tvSeriesId) {
            if (tvSeriesDetailsDictionary.containsKey(tvSeriesId)) {
                return tvSeriesDetailsDictionary.get(tvSeriesId);
            }

            final MutableLiveData<TVSeriesDetailsModel> mutableLiveData = new MutableLiveData<>();

            TVSeriesInterface apiService =
                    MovieDBAPI.getClient().create(TVSeriesInterface.class);

            apiService.getTVSeriesDetails(Integer.toString(tvSeriesId), MovieDBAPI.KEY, "en-US").enqueue(new Callback<TVSeriesDetailsModel>() {
                @Override
                public void onResponse(Call<TVSeriesDetailsModel> call, Response<TVSeriesDetailsModel> response) {
                    Log.i(TAG, "getTVSeriesDetails response=" + response);

                    if (response.isSuccessful() && response.body() != null) {
                        mutableLiveData.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<TVSeriesDetailsModel> call, Throwable t) {
                    Log.e(TAG, "getTVSeriesDetails onFailure" + call.toString());
                }
            });

        tvSeriesDetailsDictionary.put(tvSeriesId, mutableLiveData);

            return mutableLiveData;
        }

    public LiveData<MovieImagesModel> requestTVSeriesImages(int movieId) {
        if (tvSeriesImagesDictionary.containsKey(movieId)) {
            return tvSeriesImagesDictionary.get(movieId);
        }

        final MutableLiveData<MovieImagesModel> mutableLiveData = new MutableLiveData<>();

        TVSeriesInterface apiService =
                MovieDBAPI.getClient().create(TVSeriesInterface.class);

        apiService.getTVSeriesImages(Integer.toString(movieId), MovieDBAPI.KEY).enqueue(new Callback<MovieImagesModel>() {
            @Override
            public void onResponse(Call<MovieImagesModel> call, Response<MovieImagesModel> response) {
                Log.i(TAG, "getTVSeriesImages response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieImagesModel> call, Throwable t) {
                Log.e(TAG, "getTVSeriesImages onFailure" + call.toString());
            }
        });

        tvSeriesImagesDictionary.put(movieId, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<MovieCreditsModel> requestTVSeriesCredits(int movieId) {
        if (tvSeriesCreditsDictionary.containsKey(movieId)) {
            return tvSeriesCreditsDictionary.get(movieId);
        }

        final MutableLiveData<MovieCreditsModel> mutableLiveData = new MutableLiveData<>();

        TVSeriesInterface apiService =
                MovieDBAPI.getClient().create(TVSeriesInterface.class);

        apiService.getTVSeriesCredits(Integer.toString(movieId), MovieDBAPI.KEY).enqueue(new RepositoryLiveDataCallback<>(mutableLiveData, TAG, "getTVSeriesCredits"));

        tvSeriesCreditsDictionary.put(movieId, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<TVSeriesModel> requestRecommendedTVSeries(int id) {
        if (recommendedDictionary.containsKey(id)) {
            return recommendedDictionary.get(id);
        }

        final MutableLiveData<TVSeriesModel> mutableLiveData = new MutableLiveData<>();

        TVSeriesInterface apiService =
                MovieDBAPI.getClient().create(TVSeriesInterface.class);

        apiService.getRecommendedTVSeries(Integer.toString(id), MovieDBAPI.KEY).enqueue(new RepositoryLiveDataCallback<>(mutableLiveData, TAG, "getRecommendedTVSeries"));

        recommendedDictionary.put(id, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<TVSeriesModel> requestSimilarTVSeries(int id) {
        if (similarDictionary.containsKey(id)) {
            return similarDictionary.get(id);
        }

        final MutableLiveData<TVSeriesModel> mutableLiveData = new MutableLiveData<>();

        TVSeriesInterface apiService =
                MovieDBAPI.getClient().create(TVSeriesInterface.class);

        apiService.getSimilarTVSeries(Integer.toString(id), MovieDBAPI.KEY).enqueue(new RepositoryLiveDataCallback<>(mutableLiveData, TAG, "getSimilarTVSeries"));

        similarDictionary.put(id, mutableLiveData);

        return mutableLiveData;
    }

    //
    public MutableLiveData<TVSeriesModel> requestDiscoverTV(int page, Integer year, String withGenres, String airDateGte, String airDateLte, Integer voteCountGte, String sortBy, String withoutGenres, Integer runtimeLte, Integer runtimeGte, String originalLanguage) {
        final MutableLiveData<TVSeriesModel> mutableLiveData = new MutableLiveData<>();

        TVSeriesInterface apiService =
                MovieDBAPI.getClient().create(TVSeriesInterface.class);

        apiService.getDiscoverTV(MovieDBAPI.KEY, page, year, withGenres, airDateGte, airDateLte, voteCountGte, sortBy, withoutGenres, runtimeLte, runtimeGte, originalLanguage).enqueue(new Callback<TVSeriesModel>() {
            @Override
            public void onResponse(Call<TVSeriesModel> call, Response<TVSeriesModel> response) {
                Log.i(TAG, "requestDiscoverTV response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    //Log.i(TAG, "requestDiscoverMovies
                    mutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<TVSeriesModel> call, Throwable t) {
                Log.e(TAG, "getDiscoverTV onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }

}
