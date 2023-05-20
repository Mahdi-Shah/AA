package com.example.demo.view;

import com.example.demo.controller.GameController;
import com.example.demo.model.Ball;
import javafx.scene.control.Alert;

public class GameMenuController {
    private static GameMenuController menuController = null;
    public Ball[] getDefaultBalls() {
        return GameController.getInstance().getDefaultBalls();
    }

    public static GameMenuController getInstance() {
        if (menuController == null)
            menuController = new GameMenuController();
        return menuController;
    }

    public void shootBall() {
        if (GameController.getInstance().hasNotShotBall())
            GameController.getInstance().shootBall();
        else {
            (new Alert(Alert.AlertType.ERROR, "You haven't another ball")).show();
        }
    }

    public void getReadyToLaunch() {
        if (GameController.getInstance().hasNotShotBall())
            GameController.getInstance().getReadyToLaunch();
    }

    public void moveToLeft() {
        GameController.getInstance().moveToLeft(true);
    }

    public void moveToRight() {
        GameController.getInstance().moveToLeft(false);
    }

    public void setTimer(long now) {
        GameController.getInstance().setTimer(now);
    }

    public void stopTimer() {
        GameController.getInstance().stopTimer();
    }

    public void moveBalls() {
        GameController.getInstance().moveBalls();
    }

    public double getBigBallRadius() {
        return GameController.getInstance().getBigBallRadius();
    }

    public Ball[] getBalls() {
        return GameController.getInstance().getBalls();
    }

    public void doFazesFunctions() {
        GameController.getInstance().doFazesFunctions();
    }

    public double getWindSpeed() {
        return GameController.getInstance().getWindSpeed();
    }

    public int getMinutes() {
        return GameController.getInstance().getMinutes();
    }

    public int getSeconds() {
        return GameController.getInstance().getSeconds();
    }

    public double getMagicForceDegree() {
        return GameController.getInstance().getMagicForceDegree();
    }

    public boolean isGameOver() {
        return GameController.getInstance().isGameOver();
    }

    public boolean isWin() {
        return GameController.getInstance().isWin();
    }

    public double getIceProgressPercent() {
        return GameController.getInstance().getIceProgressPercent();
    }

    public void startIceProgress() {
        GameController.getInstance().startIceProgress();
    }

    public void stopIceProgress() {
        GameController.getInstance().stopIceProgress();
    }

    public double getCircleCenterX() {
        return GameController.getInstance().getCircleCenterX();
    }

    public double getCircleCenterY() {
        return GameController.getInstance().getCircleCenterY();
    }

    public double getLineWidth() {
        return GameController.getInstance().getLineWidth();
    }
}
