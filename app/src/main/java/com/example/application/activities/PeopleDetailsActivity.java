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
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.BuildConfig;
import com.example.application.DetailsInterface;
import com.example.application.R;
import com.example.application.adapters.ImagesAdapter;
import com.example.application.adapters.PeopleCombinedCreditsCastAdapter;
import com.example.application.adapters.PeopleCombinedCreditsCrewAdapter;
import com.example.application.databinding.ActivityMovieDetailsBinding;
import com.example.application.databinding.ActivityPeopleDetailsBinding;
import com.example.application.models.MovieDetailsModel;
import com.example.application.models.PeopleCombinedCreditsCastModel;
import com.example.application.models.PeopleCombinedCreditsCrewModel;
import com.example.application.models.PeopleCombinedCreditsModel;
import com.example.application.models.PeopleDetailsModel;
import com.example.application.models.PeopleImagesModel;
import com.example.application.viewmodels.MoviesViewModel;
import com.example.application.viewmodels.PeopleViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.uxcam.UXCam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@AndroidEntryPoint
public class PeopleDetailsActivity extends AppCompatActivity {
    public static final String EXTRA = "ID";
    private static final String TAG = "PeopleDetails";

    private ImagesAdapter imagesAdapter;
    private PeopleViewModel viewModel;
    private PeopleCombinedCreditsCastAdapter castAdapter;
    private PeopleCombinedCreditsCrewAdapter crewAdapter;

    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UXCam.startWithKey(BuildConfig.UX_CAM_KEY);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA,1);

        viewModel = new ViewModelProvider(this).get(PeopleViewModel.class);
        viewModel.setId(id);

        final ActivityPeopleDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_people_details);
        binding.setLifecycleOwner(this);
        binding.setModel(viewModel);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbar.setTitle("");

        imagesAdapter = new ImagesAdapter();
        binding.imagesRecyclerView.setAdapter(imagesAdapter);

        castAdapter = new PeopleCombinedCreditsCastAdapter(R.layout.card_item_credits);
        binding.castRecyclerView.setAdapter(castAdapter);

        crewAdapter = new PeopleCombinedCreditsCrewAdapter(R.layout.card_item_credits);
        binding.crewRecyclerView.setAdapter(crewAdapter);

        viewModel.getPeopleDetails().observe(this, new Observer<PeopleDetailsModel>() {
            @Override
            public void onChanged(PeopleDetailsModel model) {
                binding.collapsingToolbar.setTitle(model.getName());
                String headerImageUrl = "https://image.tmdb.org/t/p/w500" + model.getProfilePath();
                Glide.with(binding.headerImage.getContext()).load(headerImageUrl).fitCenter().into(binding.headerImage);
            }
        });

        viewModel.getPeopleImages().observe(this, new Observer<PeopleImagesModel>() {
            @Override
            public void onChanged(PeopleImagesModel model) {
                imagesAdapter.setList(model.getProfiles());
            }
        });

        viewModel.getPeopleCombinedCredits().observe(this, new Observer<PeopleCombinedCreditsModel>() {
            @Override
            public void onChanged(PeopleCombinedCreditsModel peopleCombinedCreditsModel) {

                Collections.sort(peopleCombinedCreditsModel.getCast(), new Comparator<PeopleCombinedCreditsCastModel>(){
                    public int compare(PeopleCombinedCreditsCastModel obj1, PeopleCombinedCreditsCastModel obj2) {
                        if (obj1.getReleaseDate() == null) {
                            return 1;
                        }
                        if (obj2.getReleaseDate() == null) {
                            return -1;
                        }
                        Date date1;
                        try {
                            date1 = format.parse(obj1.getReleaseDate());
                        } catch (ParseException e) {
                            return 1;
                        }
                        Date date2;
                        try {
                            date2 = format.parse(obj2.getReleaseDate());
                        } catch (ParseException e) {
                            return -1;
                        }
                        return date2.compareTo(date1);
                    }
                });
                castAdapter.setList(peopleCombinedCreditsModel.getCast());
                Collections.sort(peopleCombinedCreditsModel.getCrew(), new Comparator<PeopleCombinedCreditsCrewModel>(){
                    public int compare(PeopleCombinedCreditsCrewModel obj1, PeopleCombinedCreditsCrewModel obj2) {
                        if (obj1.getReleaseDate() == null) {
                            return 1;
                        }
                        if (obj2.getReleaseDate() == null) {
                            return -1;
                        }
                        Date date1;
                        try {
                            date1 = format.parse(obj1.getReleaseDate());
                        } catch (ParseException e) {
                            return 1;
                        }
                        Date date2;
                        try {
                            date2 = format.parse(obj2.getReleaseDate());
                        } catch (ParseException e) {
                            return -1;
                        }
                        return date2.compareTo(date1);
                    }
                });
                crewAdapter.setList(peopleCombinedCreditsModel.getCrew());
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
