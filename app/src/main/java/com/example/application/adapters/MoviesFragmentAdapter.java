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
import com.example.application.activities.DiscoverMoviesActivity;
import com.example.application.models.MoviesSectionModel;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.LinkedList;
import java.util.List;

public class MoviesFragmentAdapter extends RecyclerView.Adapter<MoviesFragmentAdapter.MoviesSectionViewHolder> {
    public static class MoviesSectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleText;
        public Button showMoreButton;
        public RecyclerView recyclerView;
        public String category;
        public String title;

        private final MoviesAdapter moviesAdapter;

        public MoviesSectionViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            showMoreButton = itemView.findViewById(R.id.show_more_button);
            showMoreButton.setOnClickListener(this);
            recyclerView = itemView.findViewById(R.id.recycler_view);

            RecyclerView.LayoutManager layoutManager = null;
            if ("B".equals(FirebaseRemoteConfig.getInstance().getString("variant"))) {
                // variant B
                layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);

                moviesAdapter = new MoviesAdapter(R.layout.card_item_vertical_b);
            } else {
                // variant A
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                layoutManager = linearLayoutManager;

                moviesAdapter = new MoviesAdapter(R.layout.card_item_vertical);
            }
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(moviesAdapter);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DiscoverMoviesActivity.class);
            intent.putExtra(DiscoverMoviesActivity.CATEGORY_EXTRA, category);
            intent.putExtra(DiscoverMoviesActivity.TITLE_EXTRA, title);
            v.getContext().startActivity(intent);
        }
    }

    private final List<MoviesSectionModel> sectionsList;

    public MoviesFragmentAdapter(List<MoviesSectionModel> sectionsList) {
        this.sectionsList = sectionsList;
    }

    @NonNull
    @Override
    public MoviesSectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_fragment_section, viewGroup, false);
        return new MoviesSectionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesSectionViewHolder holder, int i) {
        MoviesSectionModel currentItem = sectionsList.get(i);
        holder.category = currentItem.getCategory();
        holder.title = currentItem.getTitle();
        holder.titleText.setText(currentItem.getTitle());
        holder.moviesAdapter.setMovieList(currentItem.moviesList);
    }

    @Override
    public int getItemCount() {
        return sectionsList.size();
    }
}
