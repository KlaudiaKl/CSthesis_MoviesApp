package com.example.application.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.activities.BrowseMoviesActivity;
import com.example.application.activities.MovieDetailsActivity;
import com.example.application.activities.TVSerieDetailsActivity;
import com.example.application.models.MoviesModel;
import com.example.application.models.TVSeriesModel;

import java.util.LinkedList;
import java.util.List;

public class TVSeriesAdapter extends RecyclerView.Adapter<TVSeriesAdapter.ViewHolder> {
    private final int cardViewLayout;

    private List<TVSeriesModel.ResultsBean> list = new LinkedList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView mTitle;
        public TextView mLanguage;
        public int movieId;
        public String title;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewPopularMovies);
            mTitle = itemView.findViewById(R.id.textView1);
            mLanguage = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), TVSerieDetailsActivity.class);
            intent.putExtra(TVSerieDetailsActivity.EXTRA, movieId);
            v.getContext().startActivity(intent);
        }
    }

    public TVSeriesAdapter(int cardViewLayout){
        this.cardViewLayout = cardViewLayout;
    }

    public void setList(List<TVSeriesModel.ResultsBean> list) {
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
        TVSeriesModel.ResultsBean currentItem = list.get(i);
        String imageURL = "https://image.tmdb.org/t/p/w500"+currentItem.getPoster_path();
        holder.movieId = currentItem.getId();
        holder.mTitle.setText(currentItem.getName());
        holder.mLanguage.setText(currentItem.getOriginal_language());
        //Picasso.with(mContext).load(imageURL).fit().centerInside().into(holder.mImageView);
        Glide.with(holder.mImageView.getContext()).load(imageURL).fitCenter().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

