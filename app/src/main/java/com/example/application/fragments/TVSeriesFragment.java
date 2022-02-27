package com.example.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.adapters.TVSeriesFragmentAdapter;
import com.example.application.models.TVSeriesModel;
import com.example.application.models.TVSeriesSectionModel;
import com.example.application.viewmodels.TVSeriesViewModel;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TVSeriesFragment extends Fragment {

    private TVSeriesViewModel tvSeriesViewModel;
    private final ArrayList<TVSeriesSectionModel> sectionsList = new ArrayList<>();
    private TVSeriesFragmentAdapter fragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.recyclerview_fragment, container, false);

        tvSeriesViewModel = new ViewModelProvider(this).get(TVSeriesViewModel.class);

        sectionsList.clear();
        fragmentAdapter = new TVSeriesFragmentAdapter(sectionsList);
        addSection(getString(R.string.popular), TVSeriesViewModel.POPULAR_CATEGORY);
        addSection(getString(R.string.top_rated), TVSeriesViewModel.TOP_RATED_CATEGORY);
        addSection(getString(R.string.airing_today), TVSeriesViewModel.AIRING_TODAY_CATEGORY);
        addSection(getString(R.string.on_the_air), TVSeriesViewModel.ON_THE_AIR_CATEGORY);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(fragmentAdapter);
        return rootView;
    }

    private void addSection(String title, String category) {
        final int currentIndex = sectionsList.size();
        sectionsList.add(new TVSeriesSectionModel(title, category));
        tvSeriesViewModel.getTVSeriesInCategory(category, 1).observe(getViewLifecycleOwner(), new Observer<TVSeriesModel>() {
            @Override
            public void onChanged(TVSeriesModel tvSeriesModel) {
                List<TVSeriesModel.ResultsBean> results = tvSeriesModel.getResults();
                if ("B".equals(FirebaseRemoteConfig.getInstance().getString("variant"))) {
                    results = results.subList(0,6);
                }
                sectionsList.get(currentIndex).itemsList = results;
                fragmentAdapter.notifyItemChanged(currentIndex);
            }
        });
    }
}
