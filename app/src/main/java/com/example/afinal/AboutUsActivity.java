package com.example.afinal;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class AboutUsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // הגדרת ה-Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
}
