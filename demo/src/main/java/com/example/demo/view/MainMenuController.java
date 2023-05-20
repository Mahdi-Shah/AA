package com.example.demo.view;

import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class MainMenuController {
    private static MainMenuController menuController = null;


    public static MainMenuController getInstance() {
        if (menuController == null)
            menuController = new MainMenuController();
        return menuController;
    }

    public void exit(Stage stage) throws Exception {
        (new RegisterMenu()).start(stage);
    }

    public void goToScoreboard(Stage stage) throws Exception {
        (new ScoreboardMenu()).start(stage);
    }

    public void goToProfile(Stage stage) throws Exception {
        (new ProfileMenu()).start(stage);
    }

    public void playNewGame(Stage stage) throws Exception {
        GameMenu gameMenu = new GameMenu();
        gameMenu.start(stage);
    }

    public void goToSettingMenu(Stage stage) throws FileNotFoundException {
        SettingMenu menu = new SettingMenu();
        menu.start(stage);
    }
}
