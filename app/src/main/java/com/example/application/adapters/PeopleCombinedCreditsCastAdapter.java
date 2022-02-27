package com.example.application.adapters;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.activities.MovieDetailsActivity;
import com.example.application.activities.PeopleDetailsActivity;
import com.example.application.activities.TVSerieDetailsActivity;
import com.example.application.models.MovieCreditsCastModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.PeopleCombinedCreditsCastModel;

import java.util.LinkedList;
import java.util.List;

public class PeopleCombinedCreditsCastAdapter extends RecyclerView.Adapter<PeopleCombinedCreditsCastAdapter.ViewHolder> {
    private final int cardViewLayout;


    private List<PeopleCombinedCreditsCastModel> list = new LinkedList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView name;
        public TextView character;
        public TextView releaseDate;
        public int movieId;
        public String mediaType;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.text_view_1);
            releaseDate = itemView.findViewById(R.id.text_view_2);
            character = itemView.findViewById(R.id.text_view_3);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            if (mediaType.equals("tv")) {
                intent = new Intent(v.getContext(), TVSerieDetailsActivity.class);
                intent.putExtra(TVSerieDetailsActivity.EXTRA, movieId);
            } else {
                intent = new Intent(v.getContext(), MovieDetailsActivity.class);
                intent.putExtra(MovieDetailsActivity.EXTRA, movieId);
            }
            v.getContext().startActivity(intent);
        }
    }

    public PeopleCombinedCreditsCastAdapter(int cardViewLayout) {
        this.cardViewLayout = cardViewLayout;
    }

    public void setList(List<PeopleCombinedCreditsCastModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(cardViewLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        PeopleCombinedCreditsCastModel currentItem = list.get(i);
        String imageURL = "https://image.tmdb.org/t/p/w500"+currentItem.getPosterPath();

        holder.movieId = currentItem.getId();
        holder.mediaType = currentItem.getMediaType();
        holder.name.setText(TextUtils.isEmpty(currentItem.getName()) ? currentItem.getTitle() : currentItem.getName());
        holder.releaseDate.setText(TextUtils.isEmpty(currentItem.getReleaseDate()) ? "-" : currentItem.getReleaseDate());
        holder.character.setText(TextUtils.isEmpty(currentItem.getCharacter()) ? "-" : R.string.as + currentItem.getCharacter());
        Glide.with(holder.imageView.getContext()).load(imageURL).fitCenter().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

