package com.project.regame.Features.CreateData;

public class Leaderboard {
    private long id;
    private String name;
    private int scoreNumber;


    public Leaderboard(int id, String name, int scoreNumber) {
        this.id = id;
        this.name = name;
        this.scoreNumber = scoreNumber;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

}
