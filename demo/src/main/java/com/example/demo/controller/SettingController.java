package com.example.demo.controller;

import com.example.demo.model.DataBase;

public class SettingController {
    private static SettingController controller = null;
    
    public static SettingController getInstance() {
        if (controller == null)
            controller = new SettingController();
        return controller;
    }

    public void submit(int ballCount, int rotationSpeed, double windSpeed, int freezeTime, boolean isMuted, boolean isBlackAndWhite, String language) {
        DataBase.setBallNumber(ballCount);
        double rotateSpeed = rotationSpeed;
        rotateSpeed /= 100;
        DataBase.setRotateSpeed(rotateSpeed);
        DataBase.setWindRange(windSpeed);
        DataBase.setIceProgressTime(freezeTime);
        DataBase.setIsBlackAndWhite(isBlackAndWhite);
        DataBase.setCurrentGame();
        //TODO: setMute and setLanguage
//        DataBase.setMute(isMuted);
//        DataBase.setLanguage(language);


    }
}
