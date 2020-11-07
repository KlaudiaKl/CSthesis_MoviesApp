package com.example.application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
/*for popular movies*/
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movies.ResultsBean> movieList;
    private Context mContext;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mListener;


    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView mTitle;
        public TextView mLanguage;

        OnItemClickListener onItemClickListener;

        public MovieViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewPopularMovies);
            mTitle = itemView.findViewById(R.id.textView1);
            mLanguage = itemView.findViewById(R.id.textView2);

            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());

        }
    }

    public MoviesAdapter(Context context, List<Movies.ResultsBean> movieList, MoviesAdapter.OnItemClickListener onItemClickListener){
        this.mContext = context;
        this.movieList=movieList;
        this.mListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        MovieViewHolder mvh = new MovieViewHolder(v,mListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        Movies.ResultsBean currentItem = movieList.get(i);
        int movieID = currentItem.getId();
        String imageURL = "https://image.tmdb.org/t/p/w500"+currentItem.getPoster_path();
        holder.mTitle.setText(currentItem.getTitle());
        holder.mLanguage.setText(currentItem.getOriginal_language());
        //Picasso.with(mContext).load(imageURL).fit().centerInside().into(holder.mImageView);
        Glide.with(mContext).load(imageURL).fitCenter().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}

