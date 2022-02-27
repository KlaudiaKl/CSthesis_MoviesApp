package com.example.application.viewmodels;

import com.example.application.api.repositories.TVSeriesRepository;
import com.example.application.models.TVSeriesModel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiscoverTVActivityViewModel extends ViewModel {

    private final TVSeriesRepository TVrepo;

    @ViewModelInject
    public DiscoverTVActivityViewModel(TVSeriesRepository TVrepo) {
        this.TVrepo = TVrepo;
    }

    public MutableLiveData<TVSeriesModel> getDiscoverTV(int page, Integer year, String withGenres, String airDateGte, String airDateLte, Integer voteCountGte, String sortBy, String withoutGenres, Integer runtimeLte, Integer runtimeGte, String originalLanguage) {
        return TVrepo.requestDiscoverTV(page, year,withGenres, airDateGte, airDateLte, voteCountGte, sortBy,withoutGenres, runtimeLte, runtimeGte, originalLanguage);
    }


}
