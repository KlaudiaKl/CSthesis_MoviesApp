package com.example.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * getting list of popular movies from API using retrofit2
 * using recycler view and card view(card_item.xml) to display the list
 * Adapter for recycler view: MoviesAdapter.java
 */
public class PopularMovies extends AppCompatActivity implements MoviesAdapter.OnItemClickListener {

    public static String base_url = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "b73afcfcb43d313d06045cbc7c409fbc";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";
    public static final String EXTRA = "ID";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List <Movies.ResultsBean> listOfMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()
        ).build();

        APIinterface apiinterface = retrofit.create(APIinterface.class);

        Call<Movies> call = apiinterface.listOfMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies results = response.body();
                listOfMovies = results.getResults();


                recyclerView = findViewById(R.id.recyclerViewMovies);
                recyclerView.setHasFixedSize(true);
                adapter = new MoviesAdapter(PopularMovies.this,listOfMovies,(MoviesAdapter.OnItemClickListener) PopularMovies.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, Details.class);
        Movies.ResultsBean movie = listOfMovies.get(position);
        intent.putExtra(EXTRA,movie.getId());
        startActivity(intent);
    }
}
