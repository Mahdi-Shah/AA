package com.example.demo.controller;

import com.example.demo.model.Ball;
import com.example.demo.model.DataBase;
import com.example.demo.model.GameBoard;

import java.util.Random;

import static com.example.demo.view.SomeFields.WIDTH;

public class GameController {

    private static GameController controller = null;


    public static GameController getInstance() {
        if (controller == null)
            controller = new GameController();
        return controller;
    }

    public boolean hasNotShotBall() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        for (Ball ball : gameBoard.getBalls())
            if (!ball.isShot())
                return true;
        return false;
    }

    public void shootBall() {
        DataBase.getCurrentGame().shootBall();
    }

    public void getReadyToLaunch() {
        DataBase.getCurrentGame().getBall().setReadyToLaunch();
    }

    public void moveToLeft(boolean isToLeft) {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.isFourthFazeBegins())
            if (hasNotShotBall()) {
                if (isToLeft) {
                    if (gameBoard.getBall().getBallX() >= 0)
                        gameBoard.getBall().moveToLeft(true);
                } else if (gameBoard.getBall().getBallX() <= WIDTH)
                    gameBoard.getBall().moveToLeft(false);
            }
    }

    public void setTimer(long now) {
        DataBase.getCurrentGame().setTimer(now);
    }

    public void stopTimer() {
        DataBase.getCurrentGame().stopTimer();
    }

    public void moveBalls() {
        DataBase.getCurrentGame().moveBalls();
    }

    public double getBigBallRadius() {
        return DataBase.getCurrentGame().getCenterBall().getRadius();
    }

    public Ball[] getBalls() {
        return DataBase.getCurrentGame().getBalls();
    }

    public void doFazesFunctions() {
        twiceFazeFunctions();
        thirdFazeFunctions();
        fourthFazeFunctions();
    }

    public void twiceFazeFunctions() {
        if (DataBase.getCurrentGame().isTwiceFazeBegins()) {
            growingBalls();
            reverseRotation();
        }
    }

    private void reverseRotation() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.getNextRotationSecond() == 0 || gameBoard.getAllSeconds() >= gameBoard.getNextRotationSecond()) {
            Random random = new Random();
            gameBoard.setNextRotationSecond(gameBoard.getAllSeconds() + random.nextInt(4) + 4);
            gameBoard.getReverse();
        }
    }

    private void setMagicForce() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.getAllSeconds() % 5 == 0) {
            Random random = new Random();
            gameBoard.setMagicDegree(random.nextDouble(30) - 15);
        }
    }
    private void growingBalls() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.getAllSecondsDouble() - Math.floor(gameBoard.getAllSecondsDouble()) <= 0.1) {
            if (gameBoard.getAllSeconds() % 2 == 0) {
                gameBoard.setBallsRadius(1.1 * gameBoard.getBallRadius());
            } else
                gameBoard.setBallsRadius(gameBoard.getBallRadius());
        }
    }

    private void thirdFazeFunctions() {
        if (DataBase.getCurrentGame().isThirdFazeBegins())
            setBallVisibility();
    }

    private void fourthFazeFunctions() {
        if (DataBase.getCurrentGame().isFourthFazeBegins())
            setMagicForce();
    }

    private void setBallVisibility() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        gameBoard.setVisible(gameBoard.getAllSeconds() % 2 != 0);
    }

    public double getWindSpeed() {
        return DataBase.getCurrentGame().getWindSpeed();
    }

    public int getMinutes() {
        return DataBase.getCurrentGame().getMinutes();
    }

    public int getSeconds() {
        return DataBase.getCurrentGame().getSeconds();
    }

    public double getMagicForceDegree() {
        return DataBase.getCurrentGame().getMagicForceDegree();
    }

    public boolean isGameOver() {
        return DataBase.getCurrentGame().isGameOver();
    }

    public boolean isWin() {
        return DataBase.getCurrentGame().isWin();
    }

    public double getIceProgressPercent() {
        return DataBase.getCurrentGame().getIceProgressPercent();
    }

    public void startIceProgress() {
        DataBase.getCurrentGame().startIceProgress();
    }

    public void stopIceProgress() {
        DataBase.getCurrentGame().stopIceProgress();
    }
}

