package com.example.demo.controller;

import com.example.demo.model.DataBase;
import com.example.demo.model.User;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ScoreboardController {

    private static ScoreboardController controller = null;

    public void setScoreBoard(GridPane gridPane, int difficulty) {
        Label[][] bests = new Label[11][4];

        bests[0][0] = new Label("Rank");
        bests[0][1] = new Label("Username");
        bests[0][2] = new Label("Time");
        bests[0][3] = new Label("Score");
        gridPane.add(bests[0][0], 0, 0);
        gridPane.add(bests[0][1], 1, 0);
        gridPane.add(bests[0][2], 2, 0);
        gridPane.add(bests[0][3], 3, 0);
        for (int i = 1; i < 11; i++) {
            for (User user : DataBase.getUsers())
                if (user.getRankByDifficulty(difficulty) == i) {
                    bests[i][0] = new Label(String.valueOf(i));
                    bests[i][1] = new Label(user.getUsername());
                    int minutes = user.getAverageTime() / 60;
                    int seconds = user.getAverageTime() % 60;
                    bests[i][2] = new Label(minutes + ":" + seconds);
                    bests[i][3] = new Label(String.valueOf(user.getScore()));
                    gridPane.add(bests[i][0], 0, i);
                    gridPane.add(bests[i][1], 1, i);
                    gridPane.add(bests[i][2], 2, i);
                    gridPane.add(bests[i][3], 3, i);
                    gridPane.setHgap(20);
                    gridPane.setVgap(20);
                    i++;
                }
        }
    }

    public static ScoreboardController getInstance() {
        if (controller == null)
            controller = new ScoreboardController();
        return controller;
    }
}
