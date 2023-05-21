package com.example.demo.controller;

import com.example.demo.model.DataBase;

import java.io.IOException;

public class PauseController {
    private static PauseController controller = null;

    public static PauseController getInstance() {
        if (controller == null)
            controller = new PauseController();
        return controller;
    }

    public void restartGame() {
        DataBase.setCurrentGame();
    }

    public void saveGame() throws IOException {
        //TODO: fix it:
        if (DataBase.getCurrentUser() != null)
            DataBase.getCurrentUser().setSavedGame(DataBase.getCurrentGame());
        DataBase.writeDataToBase();
    }
}
