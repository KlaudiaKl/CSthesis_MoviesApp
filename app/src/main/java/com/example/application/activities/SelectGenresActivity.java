package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.application.R;
import com.example.application.adapters.CategoryAdapter;
import com.example.application.models.CategoryModel;
import com.example.application.models.GenreModel;
import com.example.application.models.GenresListModel;
import com.example.application.viewmodels.GenresViewModel;
import com.example.application.viewmodels.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

@AndroidEntryPoint
public class SelectGenresActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClicked {

    public static final String GENRE_EXTRA = "Genre";
    public static final String GENRE_ID_EXTRA = "Genre_ID";
    private GenresViewModel genresViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_genres);
        genresViewModel = new ViewModelProvider(this).get(GenresViewModel.class);
        //getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buildRecyclerView();
        createCategoryList();

    }

    private void returnGenre(String genre, int categoryId){
        Intent intent = new Intent();
        intent.putExtra(GENRE_EXTRA,genre);
        intent.putExtra(GENRE_ID_EXTRA,categoryId);
        setResult(RESULT_OK,intent);
        finish();
    }

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<GenreModel> categoryList;



    public void createCategoryList(){
        genresViewModel.getGenres().observe(this, new Observer<GenresListModel>() {
            @Override
            public void onChanged(GenresListModel genresListModel) {
                GenreModel allGenresModel = new GenreModel();
                allGenresModel.setName(getString(R.string.all));
                allGenresModel.setId(-1);
                categoryList = genresListModel.getGenres();
                categoryList.add(0,allGenresModel);
                categoryAdapter.setCategoryList(categoryList);
            }
        });

    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recyclerViewCategory);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        categoryAdapter = new CategoryAdapter(categoryList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryAdapter);
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
    public void onCategoryClicked(String category, int categoryId) {
        returnGenre(category, categoryId);
    }

    //recycler, kategorie, podpiąć kliknięcie w kategorie, na klikniecie returnGenre
}