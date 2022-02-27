package com.example.application.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.activities.BrowseMoviesActivity;
import com.example.application.activities.BrowseTVSeriesActivity;
import com.example.application.activities.DiscoverTVActivity;
import com.example.application.models.MoviesSectionModel;
import com.example.application.models.TVSeriesSectionModel;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.List;

public class TVSeriesFragmentAdapter extends RecyclerView.Adapter<TVSeriesFragmentAdapter.SectionViewHolder> {
    public static class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleText;
        public Button showMoreButton;
        public RecyclerView recyclerView;
        public String category;
        public String title;

        private final TVSeriesAdapter adapter;

        public SectionViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            showMoreButton = itemView.findViewById(R.id.show_more_button);
            showMoreButton.setOnClickListener(this);
            recyclerView = itemView.findViewById(R.id.recycler_view);

            RecyclerView.LayoutManager layoutManager = null;
            if ("B".equals(FirebaseRemoteConfig.getInstance().getString("variant"))) {
                // variant B
                layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);

                adapter = new TVSeriesAdapter(R.layout.card_item_vertical_b);
            } else {
                // variant A
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                layoutManager = linearLayoutManager;

                adapter = new TVSeriesAdapter(R.layout.card_item_vertical);
            }
            recyclerView.setLayoutManager(layoutManager);
            /*LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new TVSeriesAdapter(R.layout.card_item_vertical);*/
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DiscoverTVActivity.class);
            intent.putExtra(DiscoverTVActivity.CATEGORY_EXTRA, category);
            intent.putExtra(DiscoverTVActivity.TITLE_EXTRA, title);
            v.getContext().startActivity(intent);
        }
    }

    private final List<TVSeriesSectionModel> sectionsList;

    public TVSeriesFragmentAdapter(List<TVSeriesSectionModel> sectionsList) {
        this.sectionsList = sectionsList;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_fragment_section, viewGroup, false);
        return new SectionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SectionViewHolder holder, int i) {
        TVSeriesSectionModel currentItem = sectionsList.get(i);
        holder.category = currentItem.getCategory();
        holder.title = currentItem.getTitle();
        holder.titleText.setText(currentItem.getTitle());
        holder.adapter.setList(currentItem.itemsList);
    }

    @Override
    public int getItemCount() {
        return sectionsList.size();
    }
}
