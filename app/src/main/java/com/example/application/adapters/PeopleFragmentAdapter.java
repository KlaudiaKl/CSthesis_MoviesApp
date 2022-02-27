package com.example.application.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.activities.BrowseMoviesActivity;
import com.example.application.activities.BrowsePeopleActivity;
import com.example.application.activities.BrowseTVSeriesActivity;
import com.example.application.models.PeopleSectionModel;

import java.util.List;

public class PeopleFragmentAdapter extends RecyclerView.Adapter<PeopleFragmentAdapter.SectionViewHolder> {
    public static class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleText;
        public Button showMoreButton;
        public RecyclerView recyclerView;
        public String category;
        public String title;

        private final PeopleAdapter adapter;

        public SectionViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            showMoreButton = itemView.findViewById(R.id.show_more_button);
            showMoreButton.setOnClickListener(this);
            recyclerView = itemView.findViewById(R.id.recycler_view);

            // TODO: Handle AB test
            LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new PeopleAdapter(R.layout.card_item_vertical);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), BrowsePeopleActivity.class);
            intent.putExtra(BrowsePeopleActivity.CATEGORY_EXTRA, category);
            intent.putExtra(BrowsePeopleActivity.TITLE_EXTRA, title);
            v.getContext().startActivity(intent);
        }
    }

    private final List<PeopleSectionModel> sectionsList;

    public PeopleFragmentAdapter(List<PeopleSectionModel> sectionsList) {
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
        PeopleSectionModel currentItem = sectionsList.get(i);
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
