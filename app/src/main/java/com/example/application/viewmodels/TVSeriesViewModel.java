package com.example.application.viewmodels;

import android.graphics.Movie;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.api.repositories.TVSeriesRepository;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;
import com.example.application.models.PeopleImagesModel;
import com.example.application.models.TVSeriesDetailsModel;
import com.example.application.models.TVSeriesModel;

public class TVSeriesViewModel extends ViewModel {
    public static final String POPULAR_CATEGORY = "popular";
    public static final String TOP_RATED_CATEGORY = "top_rated";
    public static final String AIRING_TODAY_CATEGORY = "airing_today";
    public static final String ON_THE_AIR_CATEGORY = "on_the_air";

    private final TVSeriesRepository repo;
    private int id;

    @ViewModelInject
    public TVSeriesViewModel(TVSeriesRepository repo) {
        this.repo = repo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LiveData<TVSeriesModel> getPopularTVSeries() {
        return getTVSeriesInCategory(POPULAR_CATEGORY, 1);
    }

    public LiveData<TVSeriesModel> getTopRatedTVSeries() {
        return getTVSeriesInCategory(TOP_RATED_CATEGORY, 1);
    }

    public LiveData<TVSeriesModel> getTVSeriesInCategory(String category, int page) {
        return repo.requestTVSeriesInCategory(category, page);
    }

    public LiveData<TVSeriesDetailsModel> getTVSeriesDetails() {
        return repo.requestTVSeriesDetails(id);
    }

    public MutableLiveData<TVSeriesModel> getRecommendedTVSeries() {
        return repo.requestRecommendedTVSeries(id);
    }

    public MutableLiveData<TVSeriesModel> getSimilarTVSeries() {
        return repo.requestSimilarTVSeries(id);
    }

    public LiveData<MovieImagesModel> getTVSeriesImages() {
        return repo.requestTVSeriesImages(id);
    }

    public LiveData<MovieCreditsModel> getTVSeriesCredits() {
        return repo.requestTVSeriesCredits(id);
    }
}
