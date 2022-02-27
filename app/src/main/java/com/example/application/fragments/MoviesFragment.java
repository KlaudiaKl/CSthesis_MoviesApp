package com.example.application.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.activities.DiscoverMoviesActivity;
import com.example.application.activities.MovieDetailsActivity;
import com.example.application.adapters.MoviesAdapter;
import com.example.application.R;
import com.example.application.adapters.MoviesFragmentAdapter;
import com.example.application.models.MoviesModel;
import com.example.application.models.MoviesSectionModel;
import com.example.application.viewmodels.MoviesViewModel;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoviesFragment extends Fragment {
    private static final String TAG = "MoviesFragment";
    private SearchView search;

    private MoviesViewModel moviesViewModel;
    final ArrayList<MoviesSectionModel> sectionsList = new ArrayList<>();
    MoviesFragmentAdapter moviesFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.recyclerview_fragment, container, false);

        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        sectionsList.clear();
        moviesFragmentAdapter = new MoviesFragmentAdapter(sectionsList);
        addSection(getString(R.string.popular), MoviesViewModel.POPULAR_CATEGORY);
        addSection(getString(R.string.coming_soon), MoviesViewModel.COMING_SOON_CATEGORY);
        addSection(getString(R.string.top_rated), MoviesViewModel.TOP_RATED_CATEGORY);
        addSection(getString(R.string.now_playing), MoviesViewModel.NOW_PLAYING_CATEGORY);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moviesFragmentAdapter);

        return rootView;
    }

    private void addSection(String title, String category) {
        final int currentIndex = sectionsList.size();
        sectionsList.add(new MoviesSectionModel(title, category));
       /* moviesViewModel.getMoviesInCategory(category, 1).observe(getViewLifecycleOwner(), new Observer<MoviesModel>() {
            @Override
            public void onChanged(MoviesModel moviesModel) {
                List<MoviesModel.ResultsBean> results = moviesModel.getResults();
                if ("B".equals(FirebaseRemoteConfig.getInstance().getString("variant"))) {
                    results = results.subList(0,6);
                }
                sectionsList.get(currentIndex).moviesList = results;
                moviesFragmentAdapter.notifyItemChanged(currentIndex);
            }
        });*/
        String releaseDateGte = null;
        String releaseDateLte = null;
        Integer withReleaseType = null;
        String sortBy = "popularity.desc";
        int voteCountGte = 0;

   /* if(MoviesViewModel.COMING_SOON_CATEGORY.equals(category)){
     releaseDateGte = "2021-03-24";
     releaseDateLte = "2021-04-14";
     withReleaseType = DiscoverMoviesActivity.ReleaseType.THEATRICAL.ordinal();
    }
    else if(MoviesViewModel.NOW_PLAYING_CATEGORY.equals(category)){
 releaseDateGte = "2021-02-10";
 releaseDateLte ="2021-03-24";
 withReleaseType = DiscoverMoviesActivity.ReleaseType.THEATRICAL.ordinal();

    }
    else if(MoviesViewModel.POPULAR_CATEGORY.equals(category)){
        releaseDateLte = "2021-09-19";
    }
    else if(MoviesViewModel.TOP_RATED_CATEGORY.equals(category)){
    releaseDateLte = "2021-09-19";
    sortBy = "vote_average.desc";
    voteCountGte = 300;

    } */

       // moviesViewModel.getDiscoverMovies(1, null, null, withReleaseType, releaseDateGte, releaseDateLte, voteCountGte, sortBy).observe(getViewLifecycleOwner(), new Observer<MoviesModel>() {
            moviesViewModel.getMoviesInCategory(category,1).observe(getViewLifecycleOwner(), new Observer<MoviesModel>() {

        @Override
        public void onChanged(MoviesModel moviesModel) {
                List<MoviesModel.ResultsBean> results = moviesModel.getResults();
                if ("B".equals(FirebaseRemoteConfig.getInstance().getString("variant"))) {
                    results = results.subList(0,6);
                }
                sectionsList.get(currentIndex).moviesList = results;
                moviesFragmentAdapter.notifyItemChanged(currentIndex);
            }
        });
    }
}
