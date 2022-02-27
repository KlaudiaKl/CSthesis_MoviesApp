package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.application.R;
import com.example.application.adapters.CategoryAdapter;
import com.example.application.adapters.LanguagesAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectLanguageActivity extends AppCompatActivity implements LanguagesAdapter.OnLanguageClicked {

    public static final String LANG_LABEL_EXTRA = "lang" ;
    public static final String LANG_CODE_EXTRA = "code" ;

    private ArrayList<LanguagesAdapter.LanguageModel> languageList;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private LanguagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
   createLanguageList();
   buildRecyclerView();
        getSupportActionBar().setTitle(R.string.language_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view_languages);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new LanguagesAdapter(languageList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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

    public void createLanguageList(){
        languageList = new ArrayList<LanguagesAdapter.LanguageModel>();
        languageList.add(new LanguagesAdapter.LanguageModel("Any", ""));

        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.german), "de"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.polish), "pl"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.english), "en"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.arabic), "ar"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.belorussian), "be"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.chinese), "zh"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.corsican), "co"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.croatian), "hr"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.czech), "cs"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.danish), "da"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.dutch), "nl"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.finnish), "fi"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.french), "fr"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.greek), "el"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.hindi), "hi"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.hungarian), "hu"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.indonesian), "id"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.icelandic), "is"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.italian), "it"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.japanese), "ja"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.korean), "ko"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.lithuanian), "lt"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.norwegian), "no"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.romanian), "ro"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.russian), "ru"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.Slovak), "sk"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.slovenian), "sl"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.swedish), "sv"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.turkish), "tr"));
        languageList.add(new LanguagesAdapter.LanguageModel(getString(R.string.vietnamese), "vi"));
    }

    private void returnLanguage(String languageLabel, String languageCode){
        Intent intent = new Intent();
        intent.putExtra(LANG_LABEL_EXTRA, languageLabel);
        intent.putExtra(LANG_CODE_EXTRA, languageCode);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onLanguageClicked(String languageLabel, String languageCode) {
        returnLanguage(languageLabel,languageCode);
    }
}