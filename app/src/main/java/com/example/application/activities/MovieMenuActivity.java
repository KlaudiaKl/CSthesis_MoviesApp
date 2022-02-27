package com.example.application.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.BuildConfig;
import com.example.application.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.uxcam.UXCam;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieMenuActivity extends AppCompatActivity {

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UXCam.startWithKey(BuildConfig.UX_CAM_KEY);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_movie_menu);
    }
}
