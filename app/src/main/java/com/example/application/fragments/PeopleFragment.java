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
import com.example.application.adapters.PeopleFragmentAdapter;
import com.example.application.models.PeopleModel;
import com.example.application.models.PeopleSectionModel;
import com.example.application.models.TVSeriesModel;
import com.example.application.models.TVSeriesSectionModel;
import com.example.application.viewmodels.PeopleViewModel;
import com.example.application.viewmodels.TVSeriesViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PeopleFragment extends Fragment {

    private PeopleViewModel viewModel;
    private final ArrayList<PeopleSectionModel> sectionsList = new ArrayList<>();
    private PeopleFragmentAdapter fragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recyclerview_fragment, container, false);

        viewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        sectionsList.clear();
        fragmentAdapter = new PeopleFragmentAdapter(sectionsList);
        addSection(getString(R.string.popular_people), TVSeriesViewModel.POPULAR_CATEGORY);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fragmentAdapter);
        return rootView;
    }

    private void addSection(String title, String category) {
        final int currentIndex = sectionsList.size();
        sectionsList.add(new PeopleSectionModel(title, category));
        viewModel.getPeopleInCategory(category, 1).observe(getViewLifecycleOwner(), new Observer<PeopleModel>() {
            @Override
            public void onChanged(PeopleModel model) {
                // TODO: Handle AB test - limit list size to 6
                sectionsList.get(currentIndex).itemsList = model.getResults();
                fragmentAdapter.notifyItemChanged(currentIndex);
            }
        });
    }
}
