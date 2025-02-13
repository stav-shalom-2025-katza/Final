package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends BaseActivity {

    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // הגדרת סרגל הכלים (Toolbar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // קישור לכפתורי התחברות והרשמה
        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.sign_up_button);

        // כפתור להתחברות (Log In)
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // כפתור להרשמה (Sign Up)
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    // יצירת סרגל כלים
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // הגדרת פעולות כפתורי סרגל הכלים
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.results_board) {
            startActivity(new Intent(this, ResultsBoardActivity.class));
            return true;
        } else if (itemId == R.id.match_schedule) {
            startActivity(new Intent(this, MatchScheduleActivity.class));
            return true;
        } else if (itemId == R.id.league_table) {
            startActivity(new Intent(this, LeagueTableActivity.class));
            return true;
        } else if (itemId == R.id.katznelson_school) {
            startActivity(new Intent(this, KatznelsonSchoolActivity.class));
            return true;

        } else if (itemId == R.id.about_us) {
            startActivity(new Intent(this, AboutUsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
