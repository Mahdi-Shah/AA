package com.example.demo.view;

import com.example.demo.controller.GameController;
import com.example.demo.model.Ball;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class GameMenuController {
    private static GameMenuController menuController = null;

    public static GameMenuController getInstance() {
        if (menuController == null)
            menuController = new GameMenuController();
        return menuController;
    }

    public void shootBall(boolean forFirstOpponent) {
        if (GameController.getInstance().hasNotShotBall(forFirstOpponent))
            GameController.getInstance().shootBall(forFirstOpponent);
        else {
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.YOU_HAVE_NOT_ANOTHER_BALL))).show();
        }
    }

    public void getReadyToLaunch(boolean isForFirstOpponent) {
        if (GameController.getInstance().hasNotShotBall(isForFirstOpponent))
            GameController.getInstance().getReadyToLaunch(isForFirstOpponent);
    }

    public void moveToLeft(boolean forFirstOpponent) {
        GameController.getInstance().moveToLeft(true, forFirstOpponent);
    }

    public void moveToRight(boolean forFirstOpponent) {
        GameController.getInstance().moveToLeft(false, forFirstOpponent);
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
        return GameController.getInstance().getBigCircleRadius();
    }

    public ArrayList<Ball> getBalls() {
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

    public Paint getBallColor(Ball ball) {
        return GameController.getInstance().getBallColor(ball);
    }

    public KeyCode getShootFirstKey() {
        return GameController.getInstance().getShootFirstKey();
    }

    public KeyCode getLeftFirstKey() {
        return GameController.getInstance().getLeftFirstKey();
    }

    public KeyCode getRightFirstKey() {
        return GameController.getInstance().getRightFirstKey();
    }

    public KeyCode getIceKey() {
        return GameController.getInstance().getIceKey();
    }

    public KeyCode getShootSecondKey() {
        return GameController.getInstance().getShootSecondKey();
    }

    public KeyCode getLeftSecondKey() {
        return GameController.getInstance().getLeftSecondKey();
    }

    public KeyCode getRightSecondKey() {
        return GameController.getInstance().getRightSecondKey();
    }

    public boolean isTwoSomeGame() {
        return GameController.getInstance().isTwoSomeGame();
    }

    public KeyCode getStopKey() {
        return GameController.getInstance().getStopKey();
    }

    public Paint getFirstPlayerBallsRemainColor() {
        return GameController.getInstance().getFirstPlayerBallsRemainColor();
    }

    public Paint getSecondPlayerBallsRemainColor() {
        return GameController.getInstance().getSecondPlayerBallsRemainColor();
    }

    public String getFirstPlayerBallsRemain() {
        return GameController.getInstance().getFirstPlayerBallsRemain();
    }

    public String getSecondPlayerBallsRemain() {
        return GameController.getInstance().getSecondPlayerBallsRemain();
    }

    public boolean isWin() {
        return GameController.getInstance().isWin();
    }

    public String getPlayerScore(boolean isFirstPlayer) {
        return GameController.getInstance().getPlayerScore(isFirstPlayer);
    }

    public void moveWinGameBalls() {
        GameController.getInstance().moveWinGameBalls();
    }

    public void moveLostGameBalls() {
        GameController.getInstance().moveLostGameBalls();
    }
}
