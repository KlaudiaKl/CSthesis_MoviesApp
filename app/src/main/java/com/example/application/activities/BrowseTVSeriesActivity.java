package com.example.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.BuildConfig;
import com.example.application.R;
import com.example.application.adapters.MoviesAdapter;
import com.example.application.adapters.TVSeriesAdapter;
import com.example.application.models.MoviesModel;
import com.example.application.models.TVSeriesModel;
import com.example.application.viewmodels.TVSeriesViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.uxcam.UXCam;

import java.util.LinkedList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * getting list of popular movies from API using retrofit2
 * using recycler view and card view(card_item.xml) to display the list
 * Adapter for recycler view: PopularMoviesAdapter.java
 */

@AndroidEntryPoint
public class BrowseTVSeriesActivity extends AppCompatActivity {
    private static final String TAG = "BrowseTVSeries";
    public static final String CATEGORY_EXTRA = "CATEGORY_EXTRA";
    public static final String TITLE_EXTRA = "TITLE_EXTRA";
    private RecyclerView recyclerView;
    private TVSeriesAdapter adapter;
    private LinearLayoutManager layoutManager;

    private TVSeriesViewModel viewModel;
    private FirebaseAnalytics firebaseAnalytics;

    private String category;
    private boolean isLoading = true;
    private boolean hasMorePages = true;
    private int page = 1;
    private final List<TVSeriesModel.ResultsBean> list = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        UXCam.startWithKey(BuildConfig.UX_CAM_KEY);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_browse_movies);

        Intent intent = getIntent();
        category = intent.getStringExtra(CATEGORY_EXTRA);

        String title = intent.getStringExtra(TITLE_EXTRA);
        if (title == null) {
            title = getString(R.string.browse_movies);
        }
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(TVSeriesViewModel.class);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setHasFixedSize(true);
        adapter = new TVSeriesAdapter(R.layout.card_item);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Lazy loading
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (!isLoading && hasMorePages) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        Log.i(TAG, "Loading more items");
                        loadMoreItems();
                    }
                }
            }
        });
        loadMoreItems();
    }

    private void loadMoreItems() {
        Log.i(TAG, "Loading page " + page);
        isLoading = true;

        viewModel.getTVSeriesInCategory(category, page).observe(this, new Observer<TVSeriesModel>() {
            @Override
            public void onChanged(TVSeriesModel model) {
                List<TVSeriesModel.ResultsBean> results = model.getResults();
                adapter.setList(model.getResults());
                if (results.size() > 0) {
                    list.addAll(results);
                    adapter.setList(list);
                    isLoading = false;
                    page++;
                } else {
                    hasMorePages = false;
                }
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
