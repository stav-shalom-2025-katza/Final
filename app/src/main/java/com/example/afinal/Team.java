package com.example.afinal;

public class Team {
    private int id;
    private String name;
    private int gamesPlayed;
    private int points;

    // **בנאי (Constructor)**
    public Team(int id, String name, int gamesPlayed, int points) {
        this.id = id;
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.points = points;
    }

    // **Getter-ים**
    public int getId() { return id; }
    public String getName() { return name; }
    public int getGamesPlayed() { return gamesPlayed; }  // הוסף את זה
    public int getPoints() { return points; }
}
