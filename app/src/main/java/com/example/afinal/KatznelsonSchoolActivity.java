package com.example.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class KatznelsonSchoolActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katznelson_school);

        // הגדרת Toolbar באופן ידני
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // כפתור לפתיחת אתר בית הספר
        Button visitWebsiteButton = findViewById(R.id.visitWebsiteButton);
        visitWebsiteButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://katzanelson.mashov.info/"));
            startActivity(intent);
        });
    }
}
