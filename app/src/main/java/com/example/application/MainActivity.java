package com.example.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements HomepageAdapter.OnItemClickListener {


    private ArrayList<HomePageMenu> homePageMenuList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createHomepageMenu();
        recyclerView = findViewById(R.id.RV_homepage_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new HomepageAdapter(homePageMenuList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
        public void createHomepageMenu(){
            homePageMenuList = new ArrayList<>();
            homePageMenuList.add(new HomePageMenu("Movies"));
            homePageMenuList.add(new HomePageMenu("TV series"));
            homePageMenuList.add(new HomePageMenu("People"));
        }


    @Override
    public void onItemClick(int position) {
        if(position==0){
        Intent intent = new Intent(this, PopularMovies.class);
        startActivity(intent);}
    }


}
