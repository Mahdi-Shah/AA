package com.example.demo.view;

import com.example.demo.controller.GameController;
import com.example.demo.controller.PauseController;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenuController {

    private static PauseMenuController menuController = null;

    public static PauseMenuController getInstance() {
        if (menuController == null)
            menuController = new PauseMenuController();
        return menuController;
    }

    public void restartGame(Stage primaryStage) throws Exception {
        PauseController.getInstance().restartGame();
        (new GameMenu()).start(primaryStage);
    }

    public void saveGame() throws CloneNotSupportedException, IOException {
        PauseController.getInstance().saveGame();
    }
}
