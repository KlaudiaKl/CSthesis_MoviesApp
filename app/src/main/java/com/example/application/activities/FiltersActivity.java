package com.example.application.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.application.R;
import com.example.application.models.FilterModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FiltersActivity extends AppCompatActivity {
    public static final int SELECT_PERSON_CODE = 1000;
    public static final int SELECT_WITHOUT_GENRES_CODE = 1001;
    public static final int SELECT_GENRES_CODE = 1002;
    public static final int SELECT_LANGUAGE_CODE = 1003;


    public static final String FILTER_MODEL_EXTRA = "filterModel";
    public static final int FILTER_DATA = 1;
    private Button buttonReleaseDateGte;
    private Button buttonReleaseDateLte;
    private Button buttonSelectGenres;
    private Button buttonChooseLanguage;
    private Button buttonWithoutGenres;
    private Button buttonRuntimeLte;
    private Button buttonRuntimeGte;
    private Button buttonWithPerson;
    private Button buttonOk;

    private FilterModel filterModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private TextView valueReleaseDateGte;
    private TextView valueSelectGenres;
    private TextView valueReleaseDateLte;
    private TextView valueWithPerson;
    private TextView valueChooseLanguage;
    private TextView valueWithoutGenres;
    private TextView valueRuntimeLte;
    private TextView valueRuntimeGte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        getSupportActionBar().setTitle(R.string.filters);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonSelectGenres = findViewById(R.id.select_genres_button);
        valueSelectGenres = findViewById(R.id.select_genres_value);

        buttonSelectGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectGenresClick();
            }
        });

        buttonReleaseDateGte = findViewById(R.id.release_date_gte_button);
        valueReleaseDateGte = findViewById(R.id.release_date_gte_value);

        buttonReleaseDateGte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReleaseDateGteClick();
            }
        });

        buttonReleaseDateLte = findViewById(R.id.release_date_lte_button);
        valueReleaseDateLte = findViewById(R.id.release_date_lte_value);

        buttonReleaseDateLte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReleaseDateLteClick();
            }
        });
        buttonWithPerson = findViewById(R.id.with_person_button);
        valueWithPerson = findViewById(R.id.with_person_value);

        buttonWithPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWithPersonClick();
            }
        });


        buttonChooseLanguage = findViewById(R.id.language_button);
        valueChooseLanguage = findViewById(R.id.language_value);

        buttonChooseLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLanguageButtonClick();
            }
        });

        buttonWithoutGenres = findViewById(R.id.without_genres_button);
        valueWithoutGenres = findViewById(R.id.without_genres_value);

        buttonWithoutGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWithoutGenresButtonClick();
            }
        });

        buttonRuntimeLte = findViewById(R.id.runtimeLte_button);
        valueRuntimeLte = findViewById(R.id.runtimeLte_value);

        buttonRuntimeLte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRuntimeLteClick();
            }
        });

        buttonRuntimeGte = findViewById(R.id.runtimeGte_button);
        valueRuntimeGte = findViewById(R.id.runtimeGte_value);

        buttonRuntimeGte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRuntimeGteClick();
            }
        });
        filterModel = (FilterModel) getIntent().getSerializableExtra(FILTER_MODEL_EXTRA);
        buttonOk = findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filters_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.reset_button:
                filterModel = new FilterModel();
                updateView();
                setResult();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateView() {
        valueReleaseDateGte.setText(filterModel.getReleaseDateGte());
       valueReleaseDateLte.setText(filterModel.getReleaseDateLte());
        if(filterModel.getRuntimeLte()==null){
            valueRuntimeLte.setText(getString(R.string.not_set));
        }else{
            valueRuntimeLte.setText(""+filterModel.getRuntimeLte());
        }
        if(filterModel.getRuntimeGte()==null){
            valueRuntimeGte.setText(getString(R.string.not_set));
        }else{
            valueRuntimeGte.setText(""+filterModel.getRuntimeGte());
        }
       valueSelectGenres.setText(filterModel.getWithGenresLabel());
        valueWithoutGenres.setText(filterModel.getWithoutGenresLabel());
        valueWithPerson.setText(filterModel.getWithPersonLabel());
        valueChooseLanguage.setText(filterModel.getOriginalLanguage());


    }

    private void onWithPersonClick() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(SearchActivity.ONLY_PEOPLE, true);
        startActivityForResult(intent, SELECT_PERSON_CODE);


    }

    private void onLanguageButtonClick() {
        Intent intent = new Intent(this, SelectLanguageActivity.class);

        startActivityForResult(intent, SELECT_LANGUAGE_CODE);


    }

    private void onSelectGenresClick() {
        Intent intent = new Intent(this, SelectGenresActivity.class);
        startActivityForResult(intent, SELECT_GENRES_CODE);
    }

    private void onReleaseDateGteClick() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        c.set(year, monthOfYear, dayOfMonth);
                        filterModel.setReleaseDateGte(dateFormat.format(c.getTime()));
                        setResult();
                        updateView();

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    private void onReleaseDateLteClick() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        c.set(year, monthOfYear, dayOfMonth);
                        filterModel.setReleaseDateLte(dateFormat.format(c.getTime()));
                        updateView();
                        setResult();

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }


    private void onWithoutGenresButtonClick() {
        Intent intent = new Intent(this, SelectGenresActivity.class);
        startActivityForResult(intent, SELECT_WITHOUT_GENRES_CODE);
    }

    private void onRuntimeLteClick() {
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                filterModel.setRuntimeLte(hourOfDay * 60 + minute);
                setResult();
                updateView();
            }
        }, 1, 0, true);
        dialog.show();
    }


    private void onRuntimeGteClick() {
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                filterModel.setRuntimeGte(hourOfDay * 60 + minute);
                setResult();
                updateView();
            }
        }, 1, 0, true);
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_GENRES_CODE && data != null) {
            String genre = data.getStringExtra(SelectGenresActivity.GENRE_EXTRA);
            int genreId = data.getIntExtra(SelectGenresActivity.GENRE_ID_EXTRA, -1);
            if (genreId == -1) {
                filterModel.setWithGenres("");
            } else {
                filterModel.setWithGenres("" + genreId);
            }
            filterModel.setWithGenresLabel(genre);

            setResult();

        } else if (requestCode == SELECT_WITHOUT_GENRES_CODE && data != null) {
            String genre = data.getStringExtra(SelectGenresActivity.GENRE_EXTRA);
            int genreId = data.getIntExtra(SelectGenresActivity.GENRE_ID_EXTRA, -1);
            if (genreId == -1) {
                filterModel.setWithoutGenres("");
            } else {
                filterModel.setWithoutGenres("" + genreId);
            }
            filterModel.setWithoutGenresLabel(genre);
            setResult();

        } else if (requestCode == SELECT_PERSON_CODE && data != null) {
            int personID = data.getIntExtra(SearchActivity.ID_EXTRA, -1);
            String personName = data.getStringExtra(SearchActivity.NAME_EXTRA);

            if (personID == -1) {
                filterModel.setWithPeople("");
            } else {
                filterModel.setWithPeople("" + personID);
            }
            filterModel.setWithPersonLabel(personName);
            setResult();

        }
        else if (requestCode == SELECT_LANGUAGE_CODE && data != null) {
            String lang = data.getStringExtra(SelectLanguageActivity.LANG_CODE_EXTRA);
            filterModel.setOriginalLanguage(lang);
            setResult();
        }
    }

    private void setResult() {
        updateView();
        Intent intent = new Intent();
        intent.putExtra(FILTER_MODEL_EXTRA, filterModel);
        setResult(FILTER_DATA, intent);
    }
}