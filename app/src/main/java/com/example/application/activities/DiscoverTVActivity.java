package com.example.application.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.application.R;
import com.example.application.adapters.TVSeriesAdapter;
import com.example.application.models.FilterModel;
import com.example.application.models.TVSeriesModel;
import com.example.application.viewmodels.DiscoverTVActivityViewModel;
import com.example.application.viewmodels.SearchViewModel;
import com.example.application.viewmodels.TVSeriesViewModel;
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
public class DiscoverTVActivity extends AppCompatActivity {
    private String sortBy;
    private String airDateGte;
    private String airDateLte;
    private Integer voteCountGte = null;
   /* private Button buttonReleaseDateGte;
    private Button buttonReleaseDateLte;
    private Button buttonSelectGenres;*/
    private Button filterButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final int SELECT_FILTERS_CODE = 1;
    private static final String TAG = "DiscoverTV";
    private FirebaseAnalytics firebaseAnalytics;
    public static final String CATEGORY_EXTRA = "CATEGORY_EXTRA";
    public static final String TITLE_EXTRA = "TITLE_EXTRA";
    public static final int SELECT_GENRES_CODE = 1000;
    private RecyclerView recyclerView;

    private TVSeriesAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FilterModel filterModel = new FilterModel();
    private SearchViewModel searchViewModel;



    private DiscoverTVActivityViewModel viewModel;

    private String category;
    private boolean isLoading;
    private int page = 1;
    private boolean hasMorePages = true;
    private List<TVSeriesModel.ResultsBean> list = new LinkedList<>();
    private Integer year;
    private String withGenres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_discover_tv);

        Intent intent = getIntent();
        category = intent.getStringExtra(CATEGORY_EXTRA);

        String title = intent.getStringExtra(TITLE_EXTRA);
        if (title == null) {
            title = getString(R.string.discover_TV_activity_title);
        }
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewModel = new ViewModelProvider(this).get(DiscoverTVActivityViewModel.class);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerViewTV);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new TVSeriesAdapter(R.layout.card_item);
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
                        Log.i(TAG, "Loading more items");
                        loadMoreItems();
                    }
                }
            }
        });

        if(TVSeriesViewModel.POPULAR_CATEGORY.equals(category)){
            final Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH,6);
            filterModel.setAirDateLte(dateFormat.format(c.getTime()));

        }
        else if(TVSeriesViewModel.AIRING_TODAY_CATEGORY.equals(category)){
            final Calendar c = Calendar.getInstance();



            filterModel.setAirDateGte(dateFormat.format(c.getTime()));
            c.add(Calendar.HOUR, 24);

            filterModel.setAirDateLte(dateFormat.format(c.getTime()));

        }
        else if(TVSeriesViewModel.ON_THE_AIR_CATEGORY.equals(category)){
            final Calendar c = Calendar.getInstance();
            filterModel.setAirDateGte(dateFormat.format(c.getTime()));
            c.add(Calendar.DAY_OF_MONTH, 7);
            filterModel.setAirDateLte(dateFormat.format(c.getTime()));
        }
        else if(TVSeriesViewModel.TOP_RATED_CATEGORY.equals(category)){
            final Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 6);
            filterModel.setAirDateLte(dateFormat.format(c.getTime()));
            filterModel.setSortBy("vote_average.desc");
            filterModel.setVoteCountGte(150);

        }
        newSearch();

        filterButton = findViewById(R.id.filters_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFiltersClick();
            }
        });

      /*  buttonSelectGenres = findViewById(R.id.tv_select_genres);
        buttonSelectGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectGenresClick();
            }
        });

        buttonReleaseDateGte = findViewById(R.id.tv_release_date_gte);
        buttonReleaseDateGte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReleaseDateGteClick();
            }
        });

        buttonReleaseDateLte = findViewById(R.id.tv_release_date_lte);
        buttonReleaseDateLte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReleaseDateLteClick();
            }
        });*/
    }
    private void onFiltersClick() {
        Intent intent = new Intent(this, FiltersTVActivity.class);
        intent.putExtra(FiltersTVActivity.FILTER_MODEL_EXTRA, filterModel);
        startActivityForResult(intent, SELECT_FILTERS_CODE);
    }
    private void newSearch(){
        page = 1;
        list.clear();
        adapter.setList(list);
        loadMoreItems();
    }
   /* private void newSearch(Integer year, String withGenres,  String airDateGte, String airDateLte, Integer voteCountGte, String sortBy){
        this.page = 1;
        this.year = year;
        this.withGenres = withGenres;

        this.airDateGte = airDateGte;
        this.airDateLte = airDateLte;
        this.sortBy = sortBy;
        this.voteCountGte = voteCountGte;
        list.clear();
        adapter.setList(list);
        loadMoreItems();

    }*/


    private void loadMoreItems() {
        Log.i(TAG, "Loading page " + page);
        isLoading = true;
        viewModel.getDiscoverTV(page,year,filterModel.getWithGenres(), filterModel.getAirDateGte(), filterModel.getAirDateLte(), filterModel.getVoteCountGte(), filterModel.getSortBy(), filterModel.getWithoutGenres(), filterModel.getRuntimeLte(), filterModel.getRuntimeGte(), filterModel.getOriginalLanguage())
                .observe(this, new Observer<TVSeriesModel>() {
            @Override
            public void onChanged(TVSeriesModel TVmodel) {
                List<TVSeriesModel.ResultsBean> results = TVmodel.getResults();
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

  /*  private void onSelectGenresClick(){
        Intent intent = new Intent(this, SelectGenresActivity.class);
        startActivityForResult(intent, SELECT_GENRES_CODE );
    }
 private void onReleaseDateGteClick(){
     // Get Current Date
     final Calendar c = Calendar.getInstance();


     DatePickerDialog datePickerDialog = new DatePickerDialog(this,
             new DatePickerDialog.OnDateSetListener() {

                 @Override
                 public void onDateSet(DatePicker view, int year,
                                       int monthOfYear, int dayOfMonth) {


                     c.set(year, monthOfYear, dayOfMonth);
                     airDateGte = dateFormat.format(c.getTime());
                     buttonReleaseDateGte.setText(airDateGte);
                     newSearch(null, null, airDateGte, airDateLte, voteCountGte, sortBy);


                 }
             }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
     datePickerDialog.show();

 }

    private void onReleaseDateLteClick(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        c.set(year, monthOfYear, dayOfMonth);
                        airDateLte = dateFormat.format(c.getTime());
                        buttonReleaseDateLte.setText(airDateLte);
                        newSearch(null, null,  airDateGte, airDateLte, voteCountGte, sortBy);


                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_GENRES_CODE && data !=null){
           String genre =  data.getStringExtra(SelectGenresActivity.GENRE_EXTRA);
           //buttonSelectGenres.setText(genre);
           int genreId = data.getIntExtra(SelectGenresActivity.GENRE_ID_EXTRA, -1);
           int genreId = data.getIntExtra(SelectGenresActivity.GENRE_ID_EXTRA, -1);
           if(genreId==-1){
               newSearch(year, null, airDateGte, airDateLte, voteCountGte, sortBy);
           }else {
               newSearch(year, ""+genreId, airDateGte, airDateLte, voteCountGte, sortBy);
           }
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FILTERS_CODE && data != null) {
            filterModel = (FilterModel) data.getSerializableExtra(FiltersTVActivity.FILTER_MODEL_EXTRA);
            newSearch();
        }

    }
}
