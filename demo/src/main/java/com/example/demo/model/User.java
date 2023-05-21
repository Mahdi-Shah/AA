package com.example.demo.model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class User {
    private String username;

    private String password;

//    private ImageView avatar;

    private ArrayList<GameScore> gameScores;

    private int score;

    private int easyScore;

    private int middleScore;

    private int hardScore;

    private int averageTime;

    private int easyAverageTime;

    private int middleAverageTime;

    private int hardAverageTime;

    GameBoard savedGame;

    public User(String username, String password, ImageView avatar) {
        this.username = username;
        this.password = password;
//        this.avatar = avatar;
        gameScores = new ArrayList<>();
        score = easyScore = middleScore = hardScore = 0;
        averageTime = easyAverageTime = middleAverageTime = hardAverageTime = 0;
        savedGame = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEasyScore() {
        return easyScore;
    }

    public int getMiddleScore() {
        return middleScore;
    }

    public int getHardScore() {
        return hardScore;
    }

    public int getEasyAverageTime() {
        return easyAverageTime;
    }

    public int getMiddleAverageTime() {
        return middleAverageTime;
    }

    public int getHardAverageTime() {
        return hardAverageTime;
    }

    public void addToGames(GameScore gameScore) {
        if (gameScore.isWinner())
            averageTime = (averageTime * gameScores.size() + gameScore.getSeconds()) / (gameScores.size());
        gameScores.add(gameScore);
        score += gameScore.getScore();
        switch (gameScore.getDifficulty()) {
            case 1 -> {
                easyScore += gameScore.getScore();
                easyAverageTime = (easyAverageTime * 5 + gameScore.getSeconds()) / 6;
            }
            case 2 -> {
                middleScore += gameScore.getScore();
                middleAverageTime = (middleAverageTime * 5 + gameScore.getSeconds()) / 6;
            }
            case 3 -> {
                hardScore += gameScore.getScore();
                hardAverageTime = (hardAverageTime * 5 + gameScore.getSeconds()) / 6;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public GameBoard getSavedGame() {
        return savedGame;
    }

    public void setSavedGame(GameBoard savedGame) {
        GameBoard gameBoard = savedGame;
        this.savedGame = gameBoard;
    }

    public int getRankByDifficulty(int difficult) {
        int rank = 1;
        switch (difficult) {
            case 0 -> {
                for (User user : DataBase.getUsers())
                    if (isStronger(user.getScore(), user.getAverageTime(), user.getUsername()))
                        rank++;
            }
            case 1 -> {
                for (User user : DataBase.getUsers())
                    if (isStronger(user.getEasyScore(), user.getEasyAverageTime(), user.getUsername()))
                        rank++;
            }
            case 2 -> {
                for (User user : DataBase.getUsers())
                    if (isStronger(user.getMiddleScore(), user.getMiddleAverageTime(), user.getUsername()))
                        rank++;
            }
            case 3 -> {
                for (User user : DataBase.getUsers())
                    if (isStronger(user.getHardScore(), user.getHardAverageTime(), user.getUsername()))
                        rank++;
            }
        }
        return rank;
    }

    private boolean isStronger(int score, int averageTime, String username) {
        if (score > this.score)
            return true;
        else if (score == this.score) {
            if (averageTime < this.averageTime)
                return true;
            else if (averageTime == this.averageTime)
                return username.compareTo(this.username) > 0;
        }
        return false;
    }
}
