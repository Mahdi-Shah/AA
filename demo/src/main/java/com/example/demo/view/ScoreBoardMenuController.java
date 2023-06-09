package com.example.demo.view;

import javafx.stage.Stage;

public class ScoreBoardMenuController {

    private static ScoreBoardMenuController menuController = null;

    public static ScoreBoardMenuController getInstance() {
        if (menuController == null)
            menuController = new ScoreBoardMenuController();
        return menuController;
    }

    public void back(Stage stage) throws Exception {
        (new MainMenu()).start(stage);
    }
}
