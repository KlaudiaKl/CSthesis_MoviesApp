package com.example.application.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.api.repositories.MoviesRepository;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;

public class MoviesViewModel extends ViewModel {
    public static final String POPULAR_CATEGORY = "popular";
    public static final String TOP_RATED_CATEGORY = "top_rated";
    public static final String COMING_SOON_CATEGORY = "upcoming";
    public static final String NOW_PLAYING_CATEGORY = "now_playing";

    private final MoviesRepository movieRepo;
    private int id;

    @ViewModelInject
    public MoviesViewModel(MoviesRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    public MutableLiveData<MoviesModel> getPopularMovies() {
        return getMoviesInCategory(POPULAR_CATEGORY, 1);
    }

    public MutableLiveData<MoviesModel> getTopRatedMovies() {
        return getMoviesInCategory(TOP_RATED_CATEGORY, 1);
    }

    public MutableLiveData<MoviesModel> getComingSoonMovies() {
        return getMoviesInCategory(COMING_SOON_CATEGORY, 1);
    }

    public MutableLiveData<MoviesModel> getMoviesInCategory(String category, int page) {
        return movieRepo.requestMoviesInCategory(category, page);
    }

    public MutableLiveData<MovieDetailsModel> getMovieDetails() {
        return movieRepo.requestMovieDetails(id);
    }

    public LiveData<MovieImagesModel> getMovieImages() {
        return movieRepo.requestMovieImages(id);
    }

    public MutableLiveData<MoviesModel> getSimilarMovies() {
        return movieRepo.requestSimilarMovies(id, 1);
    }

    public MutableLiveData<MoviesModel> getRecommendedMovies() {
        return movieRepo.requestRecommendedMovies(id, 1);
    }

    public MutableLiveData<MovieCreditsModel> getMovieCredits() {
        return movieRepo.requestMovieCredits(id);
    }
    public MutableLiveData<MoviesModel> getDiscoverMovies(int page, Integer year, String withGenres, Integer withReleaseType, String releaseDateGte, String releaseDateLte, int voteCountGte, String sortBy, String withPeople, String withoutGenres, Integer runtimeLte, Integer runtimeGte, String originalLanguage) {
        return movieRepo.requestDiscoverMovies(page, year,withGenres, withReleaseType, releaseDateGte, releaseDateLte, voteCountGte, sortBy, withPeople, withoutGenres, runtimeLte, runtimeGte, originalLanguage);
    }

    public void setId(int id) {
        this.id = id;
    }
}
