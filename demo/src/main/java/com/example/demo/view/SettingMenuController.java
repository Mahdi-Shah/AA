package com.example.demo.view;

import com.example.demo.controller.SettingController;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingMenuController {
    private static SettingMenuController menuController = null;

    public static SettingMenuController getInstance() {
        if (menuController == null)
            menuController = new SettingMenuController();
        return menuController;
    }


    public void submit(int ballCount, int rotationSpeed, double windSpeed, int freezeTime, boolean isMuted,
                       boolean isBlackAndWhite, String language, Stage stage) throws IOException {
        if (ballCount == 0) {
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.NUMBER_OF_BALLS_CANT_BE_ZERO))).show();
            return;
        }
        if (rotationSpeed == 0) {
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.ROTATION_SPEED_CANT_BE_ZERO))).show();
            return;
        }
        SettingController.getInstance().submit(ballCount, rotationSpeed, windSpeed, freezeTime, isMuted, isBlackAndWhite, language);
        (new SettingMenu()).start(stage);
        (new Alert(Alert.AlertType.INFORMATION, Labels.getLabel(Labels.CHANGES_SAVED_SUCCESSFULLY))).show();
    }

    public void back(Stage stage) throws Exception {
        MainMenu menu = new MainMenu();
        menu.start(stage);
    }

    public void changeThisKey(String keyName, KeyCode code) {
        SettingController.getInstance().changeThisKeyTo(keyName, code);
        (new Alert(Alert.AlertType.INFORMATION, Labels.getLabel(Labels.KEY_CHANGED_SUCCESSFULLY))).show();
    }
}
