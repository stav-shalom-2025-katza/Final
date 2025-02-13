package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "league.db";
    private static final int DATABASE_VERSION = 2; // עדכון גרסת מסד הנתונים

    private static final String TABLE_TEAMS = "teams";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GAMES_PLAYED = "games_played"; // הוספת עמודת משחקים ששוחקו
    private static final String COLUMN_POINTS = "points";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_TEAMS = "CREATE TABLE " + TABLE_TEAMS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_GAMES_PLAYED + " INTEGER DEFAULT 0, " + // הוספת עמודה למשחקים ששוחקו
                COLUMN_POINTS + " INTEGER DEFAULT 0)";
        db.execSQL(CREATE_TABLE_TEAMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_TEAMS + " ADD COLUMN " + COLUMN_GAMES_PLAYED + " INTEGER DEFAULT 0");
        }
    }

    // הוספת קבוצה חדשה עם משחקים ששוחקו
    public void addTeam(String name, int gamesPlayed, int points) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_GAMES_PLAYED, gamesPlayed);
        values.put(COLUMN_POINTS, points);
        db.insert(TABLE_TEAMS, null, values);
        db.close();
    }

    // שליפת כל הקבוצות
    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TEAMS + " ORDER BY " + COLUMN_POINTS + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                Team team = new Team(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GAMES_PLAYED)), // שליפת מספר המשחקים
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_POINTS))
                );
                teamList.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return teamList;
    }
}
