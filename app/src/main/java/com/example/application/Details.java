package com.example.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.application.PopularMovies.EXTRA;

public class Details extends AppCompatActivity {
    public static String base_url = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "b73afcfcb43d313d06045cbc7c409fbc";
    public static String LANGUAGE = "en-US";
    //public static String ID = "605116";
    private TextView Overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        //String id = intent.getStringExtra(EXTRA);
        int id = intent.getIntExtra(EXTRA,1);
        String ID = Integer.toString(id);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()
        ).build();

        DetailsInterface dInterface = retrofit.create(DetailsInterface.class);

        Call<MovieDetails> call = dInterface.detailList(ID,API_KEY,LANGUAGE,PAGE);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                MovieDetails details = response.body();
                List<MovieDetails.ProductionCompaniesBean> detailss = details.getProduction_companies();
                String synopsis = details.getOverview();
                Overview = findViewById(R.id.textViewOverview);
                Overview.setText(synopsis);

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
             t.printStackTrace();
            }

        });
    }
}
