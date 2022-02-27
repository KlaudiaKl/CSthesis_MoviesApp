package com.example.application.viewmodels;

import com.example.application.api.repositories.MoviesRepository;
import com.example.application.models.MoviesModel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiscoverMoviesActivityViewModel extends ViewModel {

    private final MoviesRepository repo;

    @ViewModelInject
    public DiscoverMoviesActivityViewModel(MoviesRepository movieRepo) {
        this.repo = movieRepo;
    }

    public MutableLiveData<MoviesModel> getDiscoverMovies(int page, Integer year, String withGenres, Integer withReleaseType, String releaseDateGte, String releaseDateLte, Integer voteCountGte, String sortBy, String withPeople, String withoutGenres, Integer runtimeLte, Integer runtimeGte, String originalLanguage) {
        return repo.requestDiscoverMovies(page, year,withGenres, withReleaseType, releaseDateGte, releaseDateLte, voteCountGte, sortBy, withPeople, withoutGenres, runtimeLte, runtimeGte, originalLanguage);
    }


}
