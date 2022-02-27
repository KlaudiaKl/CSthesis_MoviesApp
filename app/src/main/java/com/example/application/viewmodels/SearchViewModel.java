package com.example.application.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.api.repositories.SearchRepository;
import com.example.application.models.SearchModel;

public class SearchViewModel extends ViewModel {

    private final SearchRepository repo;

    @ViewModelInject
    public SearchViewModel(SearchRepository repo) {
        this.repo = repo;
    }

    public LiveData<SearchModel> search(String query) {
        return repo.requestSearch(query, 1);
    }
    public LiveData<SearchModel> searchPerson(String query) {
        return repo.requestSearchPerson(query, 1);
    }
}
