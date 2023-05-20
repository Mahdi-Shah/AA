package com.example.demo.model;

public class GameScore {

    private final boolean winner;

    private final int score;

    private final int seconds;

    private final int difficulty;

    public GameScore(boolean isWinner, int score, int seconds, int difficulty) {
        this.winner = isWinner;
        this.score = score;
        this.seconds = seconds;
        this.difficulty = difficulty;
    }

    public boolean isWinner() {
        return winner;
    }

    public int getScore() {
        return score;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
