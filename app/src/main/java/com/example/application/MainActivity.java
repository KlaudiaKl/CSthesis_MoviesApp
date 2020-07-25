package com.example.application;

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

public class MainActivity extends AppCompatActivity {
public static String base_url = "https://api.themoviedb.org";
public static int PAGE = 1;
public static String API_KEY = "b73afcfcb43d313d06045cbc7c409fbc";
public static String LANGUAGE = "en-US";
public static String CATEGORY = "popular";

private TextView myTextView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      // myTextView = findViewById(R.id.text_view);

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
         List <Movies.ResultsBean> listOfMovies = results.getResults();
         /*String content = "";
         for (int i = 0; i<=10;i++) {
             Movies.ResultsBean popularMovie = listOfMovies.get(i);
             content+=popularMovie.getTitle()+"\n";
         }
         myTextView.setText(content);*/
         //ArrayList<Movies.ResultsBean> entryList = new ArrayList<>();

         recyclerView = findViewById(R.id.recyclerViewMovies);
         recyclerView.setHasFixedSize(true);
         adapter = new MoviesAdapter(listOfMovies);
         recyclerView.setAdapter(adapter);
     }

     @Override
     public void onFailure(Call<Movies> call, Throwable t) {
      t.printStackTrace();
     }
 });







    }
}
