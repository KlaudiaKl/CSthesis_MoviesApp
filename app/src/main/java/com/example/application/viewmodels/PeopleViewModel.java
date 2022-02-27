package com.example.application.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.api.repositories.PeopleRepository;
import com.example.application.api.repositories.TVSeriesRepository;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.PeopleCombinedCreditsModel;
import com.example.application.models.PeopleDetailsModel;
import com.example.application.models.PeopleImagesModel;
import com.example.application.models.PeopleModel;
import com.example.application.models.TVSeriesModel;

public class PeopleViewModel extends ViewModel {
    public static final String POPULAR_CATEGORY = "popular";

    private final PeopleRepository repo;
    private int id;

    public void setId(int id) {
        this.id = id;
    }
    @ViewModelInject
    public PeopleViewModel(PeopleRepository repo) {
        this.repo = repo;
    }

    public LiveData<PeopleModel> getPopularPeople() {
        return getPeopleInCategory(POPULAR_CATEGORY, 1);
    }

    public LiveData<PeopleModel> getPeopleInCategory(String category, int page) {
        return repo.requestPeopleInCategory(category, page);
    }

    public LiveData<PeopleDetailsModel> getPeopleDetails() {
        return repo.requestPeopleDetails(id);
    }

    public LiveData<PeopleImagesModel> getPeopleImages() {
        return repo.requestPeopleImages(id);
    }

    public LiveData<PeopleCombinedCreditsModel> getPeopleCombinedCredits() {
        return repo.requestPeopleCombinedCredits(id);
    }
}
