package com.example.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.BuildConfig;
import com.example.application.R;
import com.example.application.adapters.CastAdapter;
import com.example.application.adapters.CrewAdapter;
import com.example.application.adapters.ImagesAdapter;
import com.example.application.adapters.MoviesAdapter;
import com.example.application.databinding.ActivityMovieDetailsBinding;
import com.example.application.models.ImageModel;
import com.example.application.models.MovieCreditsModel;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.MovieImagesModel;
import com.example.application.models.MoviesModel;
import com.example.application.viewmodels.MoviesViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.uxcam.UXCam;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieDetailsActivity extends AppCompatActivity {
    public static final String EXTRA = "ID";
    private static final String TAG = "MovieDetails";

    private MoviesViewModel viewModel;
    private MoviesAdapter similarMoviesAdapter;
    private MoviesAdapter recommendedMoviesAdapter;
    private CastAdapter castAdapter;
    private CrewAdapter crewAdapter;
    private ImagesAdapter imagesAdapter;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UXCam.startWithKey(BuildConfig.UX_CAM_KEY);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intent intent = getIntent();
        final int id = intent.getIntExtra(EXTRA,1);

        viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        viewModel.setId(id);

        final ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        binding.setLifecycleOwner(this);
        binding.setModel(viewModel);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbar.setTitle("");

        imagesAdapter = new ImagesAdapter();
        binding.imagesRecyclerView.setAdapter(imagesAdapter);

        similarMoviesAdapter = new MoviesAdapter(R.layout.card_item_vertical);
        binding.similarRecyclerView.setAdapter(similarMoviesAdapter);

        recommendedMoviesAdapter = new MoviesAdapter(R.layout.card_item_vertical);
        binding.recommendedRecyclerView.setAdapter(recommendedMoviesAdapter);

        castAdapter = new CastAdapter(R.layout.card_item_vertical);
        binding.castRecyclerView.setAdapter(castAdapter);

        crewAdapter = new CrewAdapter(R.layout.card_item_vertical);
        binding.crewRecyclerView.setAdapter(crewAdapter);

        viewModel.getMovieDetails().observe(this, new Observer<MovieDetailsModel>() {
            @Override
            public void onChanged(MovieDetailsModel model) {
                binding.collapsingToolbar.setTitle(model.getTitle());
                String headerImageUrl = "https://image.tmdb.org/t/p/w500" + model.getBackdrop_path();
                Glide.with(binding.headerImage.getContext()).load(headerImageUrl).fitCenter().into(binding.headerImage);
            }
        });

        viewModel.getMovieImages().observe(this, new Observer<MovieImagesModel>() {
            @Override
            public void onChanged(MovieImagesModel movieImagesModel) {
                imagesAdapter.setList(movieImagesModel.getBackdrops());
            }
        });

        viewModel.getRecommendedMovies().observe(this, new Observer<MoviesModel>() {
            @Override
            public void onChanged(MoviesModel model) {
                recommendedMoviesAdapter.setMovieList(model.getResults());
            }
        });

        viewModel.getSimilarMovies().observe(this, new Observer<MoviesModel>() {
            @Override
            public void onChanged(MoviesModel model) {
                similarMoviesAdapter.setMovieList(model.getResults());
            }
        });

        viewModel.getMovieCredits().observe(this, new Observer<MovieCreditsModel>() {
            @Override
            public void onChanged(MovieCreditsModel movieCreditsModel) {
                castAdapter.setList(movieCreditsModel.getCast());
                crewAdapter.setList(movieCreditsModel.getCrew());
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
