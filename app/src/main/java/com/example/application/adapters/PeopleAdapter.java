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
import com.example.application.activities.TVSerieDetailsActivity;
import com.example.application.models.PeopleModel;
import com.example.application.models.PersonModel;
import com.example.application.models.TVSeriesModel;

import java.util.LinkedList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private final int cardViewLayout;

    private List<PersonModel> list = new LinkedList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView name;
        public TextView department;
        public TextView birthday;
        public int personId;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewPopularMovies);
            name = itemView.findViewById(R.id.textView1);
            department = itemView.findViewById(R.id.textView2);
            birthday = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), PeopleDetailsActivity.class);
            intent.putExtra(PeopleDetailsActivity.EXTRA, personId);
            v.getContext().startActivity(intent);
        }
    }

    public PeopleAdapter(int cardViewLayout) {
        this.cardViewLayout = cardViewLayout;
    }

    public void setList(List<PersonModel> list) {
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
        PersonModel currentItem = list.get(i);
        String imageURL = "https://image.tmdb.org/t/p/w500"+currentItem.getProfilePath();

        holder.personId = currentItem.getId();
        holder.name.setText(currentItem.getName());
        holder.department.setText(currentItem.getKnownForDepartment());
        holder.birthday.setText(currentItem.getBirthday());
        Glide.with(holder.mImageView.getContext()).load(imageURL).fitCenter().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

