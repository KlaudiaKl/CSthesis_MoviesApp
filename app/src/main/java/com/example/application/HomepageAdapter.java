package com.example.application;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.HomepageViewHolder> {
    private ArrayList<HomePageMenu> mList;

public interface OnItemClickListener{
    void onItemClick(int position);
}

private OnItemClickListener mListener;
    public HomepageAdapter(ArrayList<HomePageMenu> List, OnItemClickListener onItemClickListener) {
        mList = List;
        this.mListener = onItemClickListener;


    }


    public class HomepageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView1;
        OnItemClickListener onItemClickListener;

        public HomepageViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_homepage_menu);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public HomepageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homepage_card,viewGroup,false);
        HomepageViewHolder vh = new HomepageViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageViewHolder homepageViewHolder, int i) {
        HomePageMenu item = mList.get(i);
        homepageViewHolder.textView1.setText(item.getText1());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
