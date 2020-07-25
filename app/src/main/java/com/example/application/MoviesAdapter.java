package com.example.application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movies.ResultsBean> movieList;
    private Context mContext;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitle;
        public TextView mLanguage;

        public MovieViewHolder(View itemView) {

            super(itemView);
            //mImageView = itemView.findViewById(R.id.imageView);
            mTitle = itemView.findViewById(R.id.textView1);
            mLanguage = itemView.findViewById(R.id.textView2);
        }
    }

    public MoviesAdapter( List<Movies.ResultsBean> movieList){
        this.movieList=movieList;
        //this.mContext = mContext;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        Movies.ResultsBean currentItem = movieList.get(i);
        holder.mTitle.setText(currentItem.getTitle());
        holder.mLanguage.setText(currentItem.getOriginal_language());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}

