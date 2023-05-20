package com.example.demo.view;

import com.example.demo.controller.SettingController;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SettingMenuController {
    private static SettingMenuController menuController = null;

    public static SettingMenuController getInstance() {
        if (menuController == null)
            menuController = new SettingMenuController();
        return menuController;
    }


    public void submit(int ballCount, int rotationSpeed, double windSpeed, int freezeTime, boolean isMuted, boolean isBlackAndWhite, String language) {
        if (ballCount == 0) {
            (new Alert(Alert.AlertType.ERROR, "number of balls can't be zero")).show();
            return;
        }
        if (rotationSpeed == 0) {
            (new Alert(Alert.AlertType.ERROR, "Rotation speed can't be zero")).show();
            return;
        }
        SettingController.getInstance().submit(ballCount, rotationSpeed, windSpeed, freezeTime, isMuted, isBlackAndWhite, language);
    }

    public void back(Stage stage) throws Exception {
        MainMenu menu = new MainMenu();
        menu.start(stage);
    }
}
