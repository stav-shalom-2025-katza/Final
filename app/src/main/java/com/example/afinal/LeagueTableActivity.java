package com.example.afinal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeagueTableActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Database
        databaseHelper = new DatabaseHelper(this);

        // הוספת קבוצות לדוגמה
        databaseHelper.addTeam("כיתה י'1", 10, 20);
        databaseHelper.addTeam("כיתה י'2", 12, 25);

        // טען קבוצות מה-SQLite
        List<Team> teams = databaseHelper.getAllTeams();
        teamAdapter = new TeamAdapter(teams);
        recyclerView.setAdapter(teamAdapter);
    }
}
