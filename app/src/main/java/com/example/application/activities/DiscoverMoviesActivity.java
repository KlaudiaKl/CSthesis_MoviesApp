package com.example.application.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.application.R;
import com.example.application.adapters.MoviesAdapter;
import com.example.application.models.FilterModel;
import com.example.application.models.MoviesModel;
import com.example.application.viewmodels.DiscoverMoviesActivityViewModel;
import com.example.application.viewmodels.MoviesViewModel;
import com.example.application.viewmodels.SearchViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;


/**
 * getting list of popular movies from API using retrofit2
 * using recycler view and card view(card_item.xml) to display the list
 * Adapter for recycler view: PopularMoviesAdapter.java
 */

@AndroidEntryPoint
public class DiscoverMoviesActivity extends AppCompatActivity {


    private static final int SELECT_FILTERS_CODE = 1;

    public enum ReleaseType {NONE, PREMIERE, THEATRICAL_LIMITED, THEATRICAL, DIGITAL, PHISICAL, TV}
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final String TAG = "BrowseMovies";
    private FirebaseAnalytics firebaseAnalytics;
    public static final String CATEGORY_EXTRA = "CATEGORY_EXTRA";
    public static final String TITLE_EXTRA = "TITLE_EXTRA";
    private RecyclerView recyclerView;

    private FilterModel filterModel = new FilterModel();

    private MoviesAdapter adapter;
    private LinearLayoutManager layoutManager;


    private DiscoverMoviesActivityViewModel viewModel;
    private SearchViewModel searchViewModel;

    private String category;
    private boolean isLoading;
    private int page = 1;
    private boolean hasMorePages = true;
    private final List<MoviesModel.ResultsBean> list = new LinkedList<>();
    private Integer year;
    private Button filterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_discover_movies);

        Intent intent = getIntent();
        category = intent.getStringExtra(CATEGORY_EXTRA);

        String title = intent.getStringExtra(TITLE_EXTRA);
        if (title == null) {
            title = getString(R.string.discover_movies_activity_title);
        }
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(DiscoverMoviesActivityViewModel.class);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setHasFixedSize(true);
        adapter = new MoviesAdapter(R.layout.card_item);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (!isLoading && hasMorePages) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        Log.i(TAG, String.valueOf(R.string.loading_more_items));
                        loadMoreItems();
                    }
                }
            }
        });

        if (MoviesViewModel.COMING_SOON_CATEGORY.equals(category)) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 6);
            filterModel.setReleaseDateGte(dateFormat.format(c.getTime()));
            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 27);
            filterModel.setReleaseDateLte( dateFormat.format(c.getTime()));
            filterModel.setWithReleaseType(ReleaseType.THEATRICAL.ordinal());
        } else if (MoviesViewModel.NOW_PLAYING_CATEGORY.equals(category)) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, -36);
            filterModel.setReleaseDateGte(dateFormat.format(c.getTime()));
            c=Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 6);
            filterModel.setReleaseDateLte(dateFormat.format(c.getTime()));;
            filterModel.setWithReleaseType(ReleaseType.THEATRICAL.ordinal());

        } else if (MoviesViewModel.POPULAR_CATEGORY.equals(category)) {
            final Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 6);
            filterModel.setReleaseDateLte(dateFormat.format(c.getTime()));
        } else if (MoviesViewModel.TOP_RATED_CATEGORY.equals(category)) {

            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 6);
            filterModel.setReleaseDateLte(dateFormat.format(c.getTime()));
            filterModel.setSortBy("vote_average.desc");
            filterModel.setVoteCountGte(300);

        }
          newSearch();

        filterButton = findViewById(R.id.filters_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFiltersClick();
            }
        });


    }

    private void onFiltersClick() {
        Intent intent = new Intent(this, FiltersActivity.class);
        intent.putExtra(FiltersActivity.FILTER_MODEL_EXTRA, filterModel);
        startActivityForResult(intent, SELECT_FILTERS_CODE);
    }


    private void newSearch(){
        page = 1;
        list.clear();
        adapter.setMovieList(list);
        loadMoreItems();
    }


    private void loadMoreItems() {
        Log.i(TAG, "Loading page " + page);
        isLoading = true;
        viewModel.getDiscoverMovies(page, year, filterModel.getWithGenres(), filterModel.getWithReleaseType(), filterModel.getReleaseDateGte(), filterModel.getReleaseDateLte(), filterModel.getVoteCountGte(), filterModel.getSortBy(), filterModel.getWithPeople(), filterModel.getWithoutGenres(), filterModel.getRuntimeLte(), filterModel.getRuntimeGte(), filterModel.getOriginalLanguage() ).observe(this, new Observer<MoviesModel>() {
            @Override
            public void onChanged(MoviesModel moviesModel) {
                List<MoviesModel.ResultsBean> results = moviesModel.getResults();
                if (results.size() > 0) {
                    list.addAll(results);
                    adapter.setMovieList(list);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FILTERS_CODE && data != null) {
            filterModel = (FilterModel) data.getSerializableExtra(FiltersActivity.FILTER_MODEL_EXTRA);
            newSearch();
        }

    }


}
