package com.example.application.modules;

import com.example.application.api.repositories.MoviesRepository;
import com.example.application.api.repositories.PeopleRepository;
import com.example.application.api.repositories.SearchRepository;
import com.example.application.api.repositories.TVSeriesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;

@Module
@InstallIn(FragmentComponent.class)
public class FragmentModule {
    @Provides
    @Singleton
    static MoviesRepository provideMovieRepository() {
        return new MoviesRepository();
    }

    @Provides
    @Singleton
    static TVSeriesRepository provideTVSeriesRepository() {
        return new TVSeriesRepository();
    }

    @Provides
    @Singleton
    static PeopleRepository providePeopleRepository() {
        return new PeopleRepository();
    }

    @Provides
    @Singleton
    static SearchRepository provideSearchRepository() {
        return new SearchRepository();
    }
}
