package com.example.application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.application.R;
import com.example.application.models.CategoryModel;
import com.example.application.models.GenreModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final OnCategoryClicked onCategoryClicked;
    private List<GenreModel> categoryList;

    public interface OnCategoryClicked {
        void onCategoryClicked(String category, int categoryId);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;
        public String category;
        public int categoryId;

        public CategoryViewHolder(View categoryView) {
            super(categoryView);
            categoryName = categoryView.findViewById(R.id.category_name);
            categoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCategoryClicked.onCategoryClicked(category, categoryId);
                }
            });
        }

    }

    public CategoryAdapter(List<GenreModel> categoryList, OnCategoryClicked onCategoryClicked) {
        super();
        this.categoryList = categoryList;
        this.onCategoryClicked = onCategoryClicked;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        GenreModel currentItem = categoryList.get(position);

        holder.categoryName.setText(currentItem.getName());
        holder.category = currentItem.getName();
        holder.categoryId = currentItem.getId();
    }

    public void setCategoryList(List<GenreModel> categoryList){
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (categoryList == null) {
            return 0;
        } else {
            return categoryList.size();
        }
    }
}

