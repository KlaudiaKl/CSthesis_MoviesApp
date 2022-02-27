package com.example.application.viewmodels;

import com.example.application.api.repositories.GenreRepository;
import com.example.application.api.repositories.MoviesRepository;
import com.example.application.models.GenresListModel;
import com.example.application.models.MoviesModel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GenresViewModel extends ViewModel {

    private final GenreRepository repo;

    @ViewModelInject
    public GenresViewModel(GenreRepository repo) {
        this.repo = repo;
    }
    public MutableLiveData<GenresListModel> getGenres() {
        return repo.requestGenres();
    }


}
