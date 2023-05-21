package com.example.demo.controller;

import com.example.demo.model.DataBase;

public class StartController {
    private static StartController controller = null;

    public static StartController getInstance() {
        if (controller == null)
            controller = new StartController();
        return controller;
    }

    public void changeMap(int mapNumber) {
        DataBase.setStopDistance(120 + mapNumber * 20);
        DataBase.setNumberOfDefaultBalls(5 + mapNumber);
    }

    public boolean isCorrectName(String text) {
        if (DataBase.getUserByUsername(text) != null)
            if (!DataBase.getUserByUsername(text).equals(DataBase.getCurrentUser()))
                return true;
        return false;
    }

    public void playGame(boolean selected, String text, Integer value) {
        DataBase.setIsTwosomeGame(selected);
        DataBase.setRivalUsername(text);
        changeMap(value);
        DataBase.setCurrentGame();
    }
}
