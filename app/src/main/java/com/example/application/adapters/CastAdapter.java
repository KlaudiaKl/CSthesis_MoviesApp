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
import com.example.application.activities.PeopleDetailsActivity;
import com.example.application.models.MovieCreditsCastModel;
import com.example.application.models.PersonModel;

import java.util.LinkedList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    private final int cardViewLayout;

    private List<MovieCreditsCastModel> list = new LinkedList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView name;
        public TextView character;
        public int personId;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPopularMovies);
            name = itemView.findViewById(R.id.textView1);
            character = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), PeopleDetailsActivity.class);
            intent.putExtra(PeopleDetailsActivity.EXTRA, personId);
            v.getContext().startActivity(intent);
        }
    }

    public CastAdapter(int cardViewLayout) {
        this.cardViewLayout = cardViewLayout;
    }

    public void setList(List<MovieCreditsCastModel> list) {
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
        MovieCreditsCastModel currentItem = list.get(i);
        String imageURL = "https://image.tmdb.org/t/p/w500"+currentItem.getProfilePath();

        holder.personId = currentItem.getId();
        holder.name.setText(currentItem.getName());
        holder.character.setText(holder.character.getContext().getString(R.string.as)+ " " + currentItem.getCharacter());
        Glide.with(holder.imageView.getContext()).load(imageURL).fitCenter().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

