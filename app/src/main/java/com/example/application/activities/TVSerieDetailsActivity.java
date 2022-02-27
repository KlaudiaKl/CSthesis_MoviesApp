package com.example.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.BuildConfig;
import com.example.application.DetailsInterface;
import com.example.application.R;
import com.example.application.adapters.CastAdapter;
import com.example.application.adapters.CrewAdapter;
import com.example.application.adapters.ImagesAdapter;
import com.example.application.adapters.TVSeriesAdapter;
import com.example.application.databinding.ActivityMovieDetailsBinding;
import com.example.application.databinding.ActivityTvSeriesDetailsBinding;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;
import com.example.application.models.PeopleDetailsModel;
import com.example.application.models.PeopleImagesModel;
import com.example.application.models.TVSeriesDetailsModel;
import com.example.application.models.TVSeriesModel;
import com.example.application.viewmodels.MoviesViewModel;
import com.example.application.viewmodels.PeopleViewModel;
import com.example.application.viewmodels.TVSeriesViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.uxcam.UXCam;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@AndroidEntryPoint
public class TVSerieDetailsActivity extends AppCompatActivity {
    public static final String EXTRA = "ID";
    private static final String TAG = "TVSeriesDetails";

    private TVSeriesViewModel viewModel;
    private ImagesAdapter imagesAdapter;
    private CastAdapter castAdapter;
    private CrewAdapter crewAdapter;
    private TVSeriesAdapter recommendedAdapter;
    private TVSeriesAdapter similarAdapter;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UXCam.startWithKey(BuildConfig.UX_CAM_KEY);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intent intent = getIntent();
        final int id = intent.getIntExtra(EXTRA,1);

        viewModel = new ViewModelProvider(this).get(TVSeriesViewModel.class);
        viewModel.setId(id);

        final ActivityTvSeriesDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_series_details);
        binding.setLifecycleOwner(this);
        binding.setModel(viewModel);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbar.setTitle("");

        imagesAdapter = new ImagesAdapter();
        binding.imagesRecyclerView.setAdapter(imagesAdapter);

        castAdapter = new CastAdapter(R.layout.card_item_vertical);
        binding.castRecyclerView.setAdapter(castAdapter);

        crewAdapter = new CrewAdapter(R.layout.card_item_vertical);
        binding.crewRecyclerView.setAdapter(crewAdapter);

        recommendedAdapter = new TVSeriesAdapter(R.layout.card_item_vertical);
        binding.recommendedRecyclerView.setAdapter(recommendedAdapter);

        similarAdapter = new TVSeriesAdapter(R.layout.card_item_vertical);
        binding.similarRecyclerView.setAdapter(similarAdapter);

        viewModel.getTVSeriesDetails().observe(this, new Observer<TVSeriesDetailsModel>() {
            @Override
            public void onChanged(TVSeriesDetailsModel model) {
                binding.collapsingToolbar.setTitle(model.getName());
                String headerImageUrl = "https://image.tmdb.org/t/p/w500" + model.getBackdropPath();
                Glide.with(binding.headerImage.getContext()).load(headerImageUrl).fitCenter().into(binding.headerImage);
            }
        });

        viewModel.getTVSeriesImages().observe(this, new Observer<MovieImagesModel>() {
            @Override
            public void onChanged(MovieImagesModel model) {
                imagesAdapter.setList(model.getBackdrops());
            }
        });

        viewModel.getTVSeriesCredits().observe(this, new Observer<MovieCreditsModel>() {
            @Override
            public void onChanged(MovieCreditsModel movieCreditsModel) {
                castAdapter.setList(movieCreditsModel.getCast());
                crewAdapter.setList(movieCreditsModel.getCrew());
            }
        });

        viewModel.getRecommendedTVSeries().observe(this, new Observer<TVSeriesModel>() {
            @Override
            public void onChanged(TVSeriesModel model) {
                recommendedAdapter.setList(model.getResults());
            }
        });

        viewModel.getSimilarTVSeries().observe(this, new Observer<TVSeriesModel>() {
            @Override
            public void onChanged(TVSeriesModel model) {
                similarAdapter.setList(model.getResults());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
