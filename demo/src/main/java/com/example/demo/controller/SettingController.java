package com.example.demo.controller;

import com.example.demo.model.DataBase;
import javafx.scene.input.KeyCode;

import java.io.IOException;

import static com.example.demo.view.SomeFields.playSong;

public class SettingController {
    private static SettingController controller = null;
    
    public static SettingController getInstance() {
        if (controller == null)
            controller = new SettingController();
        return controller;
    }

    public void submit(int ballCount, int rotationSpeed, double windSpeed, int freezeTime, boolean isMuted, boolean isBlackAndWhite, String language) throws IOException {
        DataBase.setBallNumber(ballCount);
        double rotateSpeed = rotationSpeed;
        rotateSpeed /= 100;
        DataBase.setRotateSpeed(rotateSpeed);
        DataBase.setWindRange(windSpeed);
        DataBase.setIceProgressTime(freezeTime - 1);
        DataBase.setBlackAndWhite(isBlackAndWhite);
        DataBase.setMuteMenu(isMuted);
        DataBase.setLanguage(language);
        DataBase.saveSettings();
        playSong();

    }

    public void changeThisKeyTo(String keyName, KeyCode code) {
        switch (keyName) {
            case "Stop":
                DataBase.setStopGameKeyCode(code);
                break;
            case "Right (1st player)":
                DataBase.setGoRightFirstOpponent(code);
                break;
            case "Left (1st player)":
                DataBase.setGoLeftFirstOpponent(code);
                break;
            case "Right (2th player)":
                DataBase.setGoRightSecondOpponent(code);
                break;
            case "Left (2th player)":
                DataBase.setGoLeftSecondOpponent(code);
                break;
            case "Shoot (1st player)":
                DataBase.setShootBallFirstOpponent(code);
            case "Shoot (2th player)":
                DataBase.setShootBallSecondOpponent(code);
            case "Freeze":
                DataBase.setIceKey(code);
        }
    }
}
