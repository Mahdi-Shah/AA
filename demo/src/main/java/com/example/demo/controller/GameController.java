package com.example.demo.controller;

import com.example.demo.model.Ball;
import com.example.demo.model.DataBase;
import com.example.demo.model.GameBoard;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Random;

import static com.example.demo.model.Dimension.BIG_CIRCLE_RADIUS;
import static com.example.demo.model.Dimension.LINE_WIDTH;
import static com.example.demo.view.SomeFields.WIDTH;

public class GameController {

    private static GameController controller = null;


    public static GameController getInstance() {
        if (controller == null)
            controller = new GameController();
        return controller;
    }

    public boolean hasNotShotBall(boolean forFirstOpponent) {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (forFirstOpponent) {
            for (int i = 0; i < gameBoard.getBallNumber(); i++) {
                if (!gameBoard.getAllBalls().get(i).isShot())
                    return true;
            }
        } else {
            if (gameBoard.isTwosomeGame()) {
                for (int i = gameBoard.getBallNumber() + gameBoard.getDefaultBallNumber(); i < gameBoard.getAllBalls().size(); i++) {
                    if (!gameBoard.getAllBalls().get(i).isShot())
                        return true;
                }
            }
        }
        return false;
    }

    public void shootBall(boolean firstOpponentShoot) {
        if (!firstOpponentShoot && !DataBase.getCurrentGame().isTwosomeGame())
            return;
        DataBase.getCurrentGame().shootBall(firstOpponentShoot);
    }

    public void getReadyToLaunch(boolean isForFirstOpponent) {
        if (!isForFirstOpponent && !DataBase.getCurrentGame().isTwosomeGame())
            return;
        DataBase.getCurrentGame().setBallReadyToLaunch(isForFirstOpponent).setReadyToLaunch();
    }

    public void moveToLeft(boolean isToLeft, boolean isForFirstOpponent) {
        if (!isForFirstOpponent && !DataBase.getCurrentGame().isTwosomeGame())
            return;
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.isFourthFazeBegins())
            if (hasNotShotBall(isForFirstOpponent)) {
                if (isToLeft) {
                    if (gameBoard.getBall(isForFirstOpponent).getBallX() >= 22)
                        gameBoard.getBall(isForFirstOpponent).moveToLeft(true);
                } else if (gameBoard.getBall(isForFirstOpponent).getBallX() <= WIDTH - 40)
                    gameBoard.getBall(isForFirstOpponent).moveToLeft(false);
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

    public double getBigCircleRadius() {
        return BIG_CIRCLE_RADIUS;
    }

    public ArrayList<Ball> getBalls() {
        return DataBase.getCurrentGame().getAllBalls();
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

    public double getCircleCenterX() {
        return DataBase.getCurrentGame().getCenterBall().getCircleCenterX();
    }

    public double getCircleCenterY() {
        return DataBase.getCurrentGame().getCenterBall().getCircleCenterY();
    }

    public double getLineWidth() {
        return LINE_WIDTH;
    }

    public Paint getBallColor(Ball ball) {
        if (DataBase.isBlackAndWhite() || ball.isDefaultBall())
            return Color.BLACK;
        else {
            if (ball.isForFirstOpponent())
                return Color.BLUE;
            else
                return Color.GOLD;
        }
    }

    public KeyCode getShootFirstKey() {
        return DataBase.getShootBallFirstOpponent();
    }

    public KeyCode getLeftFirstKey() {
        return DataBase.getGoLeftFirstOpponent();
    }

    public KeyCode getRightFirstKey() {
        return DataBase.getGoRightFirstOpponent();
    }

    public KeyCode getIceKey() {
        return DataBase.getIceKey();
    }

    public KeyCode getShootSecondKey() {
        return DataBase.getShootBallSecondOpponent();
    }

    public KeyCode getLeftSecondKey() {
        return DataBase.getGoLeftSecondOpponent();
    }

    public KeyCode getRightSecondKey() {
        return DataBase.getGoRightSecondOpponent();
    }

    public boolean isTwoSomeGame() {
        return DataBase.getCurrentGame().isTwosomeGame();
    }

    public Paint getFirstPlayerBallsRemainColor() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.getFirstPlayerBallsRemain() > 15)
            return Color.PURPLE;
        else if (gameBoard.getFirstPlayerBallsRemain() > 10)
            return Color.RED;
        else if (gameBoard.getFirstPlayerBallsRemain() > 5)
            return Color.YELLOW;
        else 
            return Color.GREEN;
    }

    public Paint getSecondPlayerBallsRemainColor() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.getSecondPlayerBallsRemain() > 15)
            return Color.PURPLE;
        else if (gameBoard.getSecondPlayerBallsRemain() > 10)
            return Color.RED;
        else if (gameBoard.getSecondPlayerBallsRemain() > 5)
            return Color.YELLOW;
        else
            return Color.GREEN;
    }

    public String getFirstPlayerBallsRemain() {
        return String.valueOf(DataBase.getCurrentGame().getFirstPlayerBallsRemain());
    }

    public String getSecondPlayerBallsRemain() {
        return String.valueOf(DataBase.getCurrentGame().getSecondPlayerBallsRemain());
    }
}

