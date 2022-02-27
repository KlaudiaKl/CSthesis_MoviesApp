package com.example.application.api.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.application.api.MovieDBAPI;
import com.example.application.api.interfaces.MovieAPIInterface;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;

import java.util.HashMap;
import java.util.Locale;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    private static final String TAG = "MovieRepo";

    private final HashMap<String, MutableLiveData<MoviesModel>> mutableLiveDataDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<MovieDetailsModel>> movieDetailsDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<MovieImagesModel>> movieImagesDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<MoviesModel>> similarMoviesDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<MoviesModel>> recommendedMoviesDictionary = new HashMap<>();
    private final HashMap<Integer, MutableLiveData<MovieCreditsModel>> movieCreditsDictionary = new HashMap<>();
    private String lang = "en-US";
    @Inject
    public MoviesRepository() {
lang = Locale.getDefault().getLanguage();
    }

    public MutableLiveData<MoviesModel> requestMoviesInCategory(String category, int page) {

        if (mutableLiveDataDictionary.containsKey(category + page)) {
            return mutableLiveDataDictionary.get(category + page);
        }

        final MutableLiveData<MoviesModel> mutableLiveData = new MutableLiveData<>();

        MovieAPIInterface apiService =
                MovieDBAPI.getClient().create(MovieAPIInterface.class);

        apiService.getMoviesInCategory(category, MovieDBAPI.KEY, lang, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                Log.i(TAG, "getMoviesInCategory response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "getMoviesInCategory response.size=" + response.body().getResults().size());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e(TAG, "getMoviesInCategory onFailure" + call.toString());
            }
        });

        mutableLiveDataDictionary.put(category + page, mutableLiveData);

        return mutableLiveData;
    }


    public MutableLiveData<MoviesModel> requestSimilarMovies(int movieId, int page) {

        if (similarMoviesDictionary.containsKey(movieId)) {
            return similarMoviesDictionary.get(movieId);
        }

        final MutableLiveData<MoviesModel> mutableLiveData = new MutableLiveData<>();

        MovieAPIInterface apiService =
                MovieDBAPI.getClient().create(MovieAPIInterface.class);

        apiService.getSimilarMovies(Integer.toString(movieId), MovieDBAPI.KEY, lang, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {

                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e(TAG, "getSimilarMovies onFailure" + call.toString());
            }
        });

        similarMoviesDictionary.put(movieId, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<MoviesModel> requestRecommendedMovies(int movieId, int page) {

        if (recommendedMoviesDictionary.containsKey(movieId)) {
            return recommendedMoviesDictionary.get(movieId);
        }

        final MutableLiveData<MoviesModel> mutableLiveData = new MutableLiveData<>();

        MovieAPIInterface apiService =
                MovieDBAPI.getClient().create(MovieAPIInterface.class);

        apiService.getRecommendedMovies(Integer.toString(movieId), MovieDBAPI.KEY, lang, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                Log.i(TAG, "getMoviesInCategory response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "getMoviesInCategory response.size=" + response.body().getResults().size());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e(TAG, "getMoviesInCategory onFailure" + call.toString());
            }
        });

        recommendedMoviesDictionary.put(movieId, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<MovieDetailsModel> requestMovieDetails(int movieId) {
        if (movieDetailsDictionary.containsKey(movieId)) {
            return movieDetailsDictionary.get(movieId);
        }

        final MutableLiveData<MovieDetailsModel> mutableLiveData = new MutableLiveData<>();

        MovieAPIInterface apiService =
                MovieDBAPI.getClient().create(MovieAPIInterface.class);

        apiService.getMovieDetails(Integer.toString(movieId), MovieDBAPI.KEY, lang).enqueue(new Callback<MovieDetailsModel>() {
            @Override
            public void onResponse(Call<MovieDetailsModel> call, Response<MovieDetailsModel> response) {
                Log.i(TAG, "getMovieDetails response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsModel> call, Throwable t) {
                Log.e(TAG, "getMovieDetails onFailure" + call.toString());
            }
        });

        movieDetailsDictionary.put(movieId, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<MovieImagesModel> requestMovieImages(int movieId) {
        if (movieImagesDictionary.containsKey(movieId)) {
            return movieImagesDictionary.get(movieId);
        }

        final MutableLiveData<MovieImagesModel> mutableLiveData = new MutableLiveData<>();

        MovieAPIInterface apiService =
                MovieDBAPI.getClient().create(MovieAPIInterface.class);

        apiService.getMovieImages(Integer.toString(movieId), MovieDBAPI.KEY).enqueue(new Callback<MovieImagesModel>() {
            @Override
            public void onResponse(Call<MovieImagesModel> call, Response<MovieImagesModel> response) {
                Log.i(TAG, "getMovieImages response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieImagesModel> call, Throwable t) {
                Log.e(TAG, "getMovieImages onFailure" + call.toString());
            }
        });

        movieImagesDictionary.put(movieId, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<MovieCreditsModel> requestMovieCredits(int movieId) {
        if (movieCreditsDictionary.containsKey(movieId)) {
            return movieCreditsDictionary.get(movieId);
        }

        final MutableLiveData<MovieCreditsModel> mutableLiveData = new MutableLiveData<>();

        MovieAPIInterface apiService =
                MovieDBAPI.getClient().create(MovieAPIInterface.class);

        apiService.getMovieCredits(Integer.toString(movieId), MovieDBAPI.KEY).enqueue(new RepositoryLiveDataCallback<>(mutableLiveData, TAG, "getMovieCredits"));

        movieCreditsDictionary.put(movieId, mutableLiveData);

        return mutableLiveData;
    }

    public MutableLiveData<MoviesModel> requestDiscoverMovies(int page, Integer year, String withGenres, Integer withReleaseType, String releaseDateGte, String releaseDateLte, Integer voteCountGte, String sortBy, String withPeople, String withoutGenres, Integer runtimeLte, Integer runtimeGte, String originalLanguage) {
        final MutableLiveData<MoviesModel> mutableLiveData = new MutableLiveData<>();

        MovieAPIInterface apiService =
                MovieDBAPI.getClient().create(MovieAPIInterface.class);

        apiService.getDiscoverMovies(MovieDBAPI.KEY, page, year, withGenres, withReleaseType, releaseDateGte, releaseDateLte, voteCountGte, sortBy, withPeople, withoutGenres,runtimeLte, runtimeGte, originalLanguage).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                Log.i(TAG, "requestDiscoverMovies response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    //Log.i(TAG, "requestDiscoverMovies
                    mutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e(TAG, "getDiscoverMovies onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
}
