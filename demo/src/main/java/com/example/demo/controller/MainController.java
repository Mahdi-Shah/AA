package com.example.demo.controller;

import com.example.demo.model.DataBase;

import java.io.IOException;

public class MainController {
    private static MainController controller = null;

    public static MainController getInstance() {
        if (controller == null)
            controller = new MainController();
        return controller;
    }

    public boolean hasSavesGame() {
        if (DataBase.getCurrentUser() != null)
            if (DataBase.getCurrentUser().getSavedGame() != null) {
                DataBase.setCurrentGame(DataBase.getCurrentUser().getSavedGame());
                return true;
            }
        return false;
    }

    public void exit() throws IOException {
        DataBase.setCurrentUser(null);
        DataBase.writeDataToBase();
    }
}
