package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // כל קוד משותף לכל הפעילויות שלך יכול להיות כאן
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // ודא ששם הקובץ נכון
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.results_board) {
            Intent intent = new Intent(this, ResultsBoardActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.match_schedule) {
            Intent intent = new Intent(this, MatchScheduleActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.league_table) {
            Intent intent = new Intent(this, LeagueTableActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.katznelson_school) {
            Intent intent = new Intent(this, KatznelsonSchoolActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.about_us) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // הוספת ה-Toolbar כ-SupportActionBar
    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.toolBar);  // ודא שה-Toolbar קיים בקובץ ה-XML של כל פעילות
        setSupportActionBar(toolbar);
    }
}
