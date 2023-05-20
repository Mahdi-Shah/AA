package com.example.demo.view;

import com.example.demo.controller.ScoreboardController;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ScoreBoardMenuController {

    private static ScoreBoardMenuController menuController = null;

    public void setScoreBoardByDifficulty(int difficulty, GridPane gridPane) {
        ScoreboardController controller = new ScoreboardController();
        controller.setScoreBoard(gridPane, difficulty);
    }

    public static ScoreBoardMenuController getInstance() {
        if (menuController == null)
            menuController = new ScoreBoardMenuController();
        return menuController;
    }

    public void back(Stage stage) throws Exception {
        (new MainMenu()).start(stage);
    }
}
