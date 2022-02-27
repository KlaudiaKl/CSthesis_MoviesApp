package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.application.BuildConfig;
import com.example.application.R;
import com.example.application.adapters.PeopleAdapter;
import com.example.application.adapters.SearchAdapter;
import com.example.application.models.PeopleModel;
import com.example.application.models.SearchModel;
import com.example.application.models.SearchModelItem;
import com.example.application.viewmodels.PeopleViewModel;
import com.example.application.viewmodels.SearchViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.uxcam.UXCam;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchAdapter.OnIdSelectedListener {

    public static final String ONLY_PEOPLE = "onlyPeople";
    public static final String ID_EXTRA = "IDextra" ;
    public static final String NAME_EXTRA = "nameExtra";
    private static final int PEOPLE_SELECT_RESULT = 1 ;
    private SearchViewModel viewModel;
    private SearchAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseAnalytics firebaseAnalytics;
    private boolean onlyPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UXCam.startWithKey(BuildConfig.UX_CAM_KEY);
        setContentView(R.layout.activity_search);

        onlyPeople = getIntent().getBooleanExtra(ONLY_PEOPLE, false);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SearchAdapter(R.layout.card_item, this);
        recyclerView.setAdapter(adapter);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        //bez domyślnego ustawiania ikony
        searchView.setIconified(false);
        ImageView iconImage = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        iconImage.setVisibility(View.GONE);
        iconImage.setImageDrawable(null);
        searchView.setSubmitButtonEnabled(false);
        //bez przycisku submit,
        //lista automatycznie ładuje się dynamicznie
        searchView.setOnQueryTextListener(this);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if(onlyPeople){
        viewModel.searchPerson(query).observe(this, new Observer<SearchModel>() {
            @Override
            public void onChanged(SearchModel model) {
                adapter.setList(model.getResults());
            }
        });}
        else{
            viewModel.search(query).observe(this, new Observer<SearchModel>() {
                @Override
                public void onChanged(SearchModel model) {
                    adapter.setList(model.getResults());
                }
            });
        }
        return true;
    }

    @Override
    public void onIDSelected(int id, String name, String mediaType) {
        if(onlyPeople) {
            Intent data = new Intent();
            data.putExtra(ID_EXTRA, id);
            data.putExtra(NAME_EXTRA,name);
            setResult(PEOPLE_SELECT_RESULT, data);
            finish();
        }
        else{
            if (SearchModelItem.MEDIA_TYPE_MOVIE.equals(mediaType)) {
                Intent intent = new Intent(this, MovieDetailsActivity.class);
                intent.putExtra(MovieDetailsActivity.EXTRA, id);
                startActivity(intent);
            } else if (SearchModelItem.MEDIA_TYPE_TV.equals(mediaType)) {
                Intent intent = new Intent(this, TVSerieDetailsActivity.class);
                intent.putExtra(TVSerieDetailsActivity.EXTRA, id);
                startActivity(intent);
            } else if (SearchModelItem.MEDIA_TYPE_PERSON.equals(mediaType)) {
                Intent intent = new Intent(this, PeopleDetailsActivity.class);
                intent.putExtra(PeopleDetailsActivity.EXTRA, id);
                startActivity(intent);
            }
        }
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