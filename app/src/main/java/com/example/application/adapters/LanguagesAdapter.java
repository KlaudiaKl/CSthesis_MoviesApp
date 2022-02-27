package com.example.application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.application.R;
import com.example.application.models.GenreModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder> {
    private final LanguagesAdapter.OnLanguageClicked onLanguageClicked;
    private List<LanguageModel> LanguageList;
public static class LanguageModel{
    public String languageLabel;
    public String languageCode;
    public LanguageModel(String languageLabel, String languageCode){
        this.languageLabel = languageLabel;
        this.languageCode = languageCode;
    }
}
    public interface OnLanguageClicked {
        void onLanguageClicked(String languageLabel, String languageCode);
    }

    public class LanguagesViewHolder extends RecyclerView.ViewHolder {
        public TextView LanguageView;
        public String LanguageLabel;
        public String LanguageCode;

        public LanguagesViewHolder(View view) {
            super(view);
            LanguageView = view.findViewById(R.id.category_name);
            LanguageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLanguageClicked.onLanguageClicked(LanguageLabel, LanguageCode);
                }
            });
        }

    }

    public LanguagesAdapter(List<LanguageModel> LanguageList, LanguagesAdapter.OnLanguageClicked onLanguageClicked) {
        super();
        this.LanguageList = LanguageList;
        this.onLanguageClicked = onLanguageClicked;
    }

    @Override
    public LanguagesAdapter.LanguagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        LanguagesAdapter.LanguagesViewHolder vh = new LanguagesAdapter.LanguagesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LanguagesAdapter.LanguagesViewHolder holder, int position) {

        LanguageModel currentItem = LanguageList.get(position);

        holder.LanguageView.setText(currentItem.languageLabel);
        holder.LanguageCode = currentItem.languageCode;
        holder.LanguageLabel = currentItem.languageLabel;


    }

    public void setLanguageList(List<LanguageModel> LanguageList){
        this.LanguageList = LanguageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (LanguageList == null) {
            return 0;
        } else {
            return LanguageList.size();
        }
    }
}
