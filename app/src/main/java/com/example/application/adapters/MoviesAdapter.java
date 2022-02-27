package com.example.application.adapters;

import android.content.Context;
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
import com.example.application.activities.MovieDetailsActivity;
import com.example.application.models.MoviesModel;

import java.util.LinkedList;
import java.util.List;

/*for popular movies*/
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private final int cardViewLayout;

    private List<MoviesModel.ResultsBean> movieList = new LinkedList<>();


    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView mTitle;
        public TextView mLanguage;
        public TextView genre;
        public TextView releaseDate;
        public int movieId;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewPopularMovies);
            mTitle = itemView.findViewById(R.id.textView1);
            mLanguage = itemView.findViewById(R.id.textView2);
           releaseDate = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.EXTRA, movieId);
            v.getContext().startActivity(intent);
        }
    }

    public MoviesAdapter(int cardViewLayout){
        this.cardViewLayout = cardViewLayout;
    }

    public void setMovieList(List<MoviesModel.ResultsBean> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(cardViewLayout, viewGroup, false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        MoviesModel.ResultsBean currentItem = movieList.get(i);
        String imageURL = "https://image.tmdb.org/t/p/w500"+currentItem.getPoster_path();
        holder.movieId = currentItem.getId();
        holder.mTitle.setText(currentItem.getTitle());
        holder.mLanguage.setText(currentItem.getOriginal_language());
        holder.releaseDate.setText(currentItem.getRelease_date());
        //Picasso.with(mContext).load(imageURL).fit().centerInside().into(holder.mImageView);
        Glide.with(holder.mImageView.getContext()).load(imageURL).fitCenter().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}

