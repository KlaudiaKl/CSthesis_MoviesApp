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
import com.example.application.activities.MovieDetailsActivity;
import com.example.application.activities.PeopleDetailsActivity;
import com.example.application.activities.TVSerieDetailsActivity;
import com.example.application.models.PeopleModel;
import com.example.application.models.SearchModel;
import com.example.application.models.SearchModelItem;

import java.util.LinkedList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private final int cardViewLayout;
    private final OnIdSelectedListener onIdSelectedListener;

    private List<SearchModelItem> list = new LinkedList<>();
public interface OnIdSelectedListener{
    void onIDSelected(int id, String name, String mediaType);

}
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView nameView;
        public TextView type;
        public int id;
        public String mediaType;
        public String name;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPopularMovies);
            nameView = itemView.findViewById(R.id.textView1);
            type = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SearchAdapter.this.onIdSelectedListener.onIDSelected(id, name, mediaType);
           /* if (SearchModelItem.MEDIA_TYPE_MOVIE.equals(mediaType)) {
                Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
                intent.putExtra(MovieDetailsActivity.EXTRA, id);
                v.getContext().startActivity(intent);
            } else if (SearchModelItem.MEDIA_TYPE_TV.equals(mediaType)) {
                Intent intent = new Intent(v.getContext(), TVSerieDetailsActivity.class);
                intent.putExtra(TVSerieDetailsActivity.EXTRA, id);
                v.getContext().startActivity(intent);
            } else if (SearchModelItem.MEDIA_TYPE_PERSON.equals(mediaType)) {
                Intent intent = new Intent(v.getContext(), PeopleDetailsActivity.class);
                intent.putExtra(PeopleDetailsActivity.EXTRA, id);
                v.getContext().startActivity(intent);
            }*/
        }
    }

    public SearchAdapter(int cardViewLayout, OnIdSelectedListener onIdSelectedListener) {
        this.cardViewLayout = cardViewLayout;
        this.onIdSelectedListener = onIdSelectedListener;
    }

    public void setList(List<SearchModelItem> list) {
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
        SearchModelItem currentItem = list.get(i);

        String imageURL = "https://image.tmdb.org/t/p/w500"+currentItem.getImagePath();

        holder.nameView.setText(currentItem.getName());
        holder.name = currentItem.getName();
       if (currentItem.getMediaType() != null) {

           holder.type.setText(currentItem.getMediaType().toUpperCase());

       }
       else{
           holder.type.setText("");
       }
       holder.mediaType = currentItem.getMediaType();
        holder.id = currentItem.getId();
        Glide.with(holder.imageView.getContext()).load(imageURL).fitCenter().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

