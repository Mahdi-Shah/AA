package com.example.demo.controller;

import com.example.demo.model.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Random;

import static com.example.demo.model.Dimension.*;
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

    public void getReadyToLaunch(boolean isForFirstOpponent) {
        if (!isForFirstOpponent && !DataBase.getCurrentGame().isTwosomeGame())
            return;
        setBallReadyToLaunch(isForFirstOpponent).setReadyToLaunch();
    }

    public void moveToLeft(boolean isToLeft, boolean isForFirstOpponent) {
        if (!isForFirstOpponent && !DataBase.getCurrentGame().isTwosomeGame())
            return;
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.isFourthFazeBegins())
            if (hasNotShotBall(isForFirstOpponent)) {
                if (isToLeft) {
                    if (getBall(isForFirstOpponent).getBallX() >= 22)
                        getBall(isForFirstOpponent).moveToLeft(true);
                } else if (getBall(isForFirstOpponent).getBallX() <= WIDTH - 40)
                    getBall(isForFirstOpponent).moveToLeft(false);
            }
    }

    public void setTimer(long now) {
        DataBase.getCurrentGame().setTimer(now);
    }

    public void stopTimer() {
        DataBase.getCurrentGame().stopTimer();
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
        setVisible(gameBoard.getAllSeconds() % 2 != 0);
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

    public double getIceProgressPercent() {
        return DataBase.getCurrentGame().getIceProgressPercent();
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

    public KeyCode getStopKey() {
        return DataBase.getStopGameKeyCode();
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

    public void stopIceProgress() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.getIceProgress().isInIceProgressTime())
            if (gameBoard.getAllSeconds() - gameBoard.getIceProgress().getWhenIceProgressBegins() >
                    gameBoard.getIceProgress().getIceProgressTime()) {
                gameBoard.setBallsNormalSpeed();
                gameBoard.getIceProgress().setInIceProgressTime(false);
            }
    }

    public void startIceProgress() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.getIceProgress().getIceProgressPercent() == 1) {
            gameBoard.getIceProgress().setWhenIceProgressBegins(gameBoard.getAllSeconds());
            gameBoard.getIceProgress().setIceProgressPercent(0);
            gameBoard.setBallsIcySpeed();
            gameBoard.getIceProgress().setInIceProgressTime(true);
        }
    }

    public void setVisible(boolean visibility) {
        DataBase.getCurrentGame().setVisibilityMode(visibility);
        for (Ball ball : DataBase.getCurrentGame().getAllBalls()) {
            if (ball.isShot() || ball.isDefaultBall())
                ball.setVisible(visibility);
        }
    }

    public boolean isGameOver() {
        if (isLost() || isWin()) {
            setVisible(true);
            return true;
        }
        return false;
    }

    public boolean isWin() {
        for (int i = 0; i < DataBase.getCurrentGame().getBallNumber(); i++)
            if (!DataBase.getCurrentGame().getAllBalls().get(i).isConnect())
                return false;
        return true;
    }

    private double distanceBetweenTwoBalls(Ball ball1, Ball ball2) {
        return Math.sqrt((Math.pow((ball1.getBallX() - ball2.getBallX()), 2)) + (Math.pow((ball1.getBallY() - ball2.getBallY()), 2)));
    }

    private boolean hasCollapseBall() {
        for (Ball ball : DataBase.getCurrentGame().getAllBalls())
            if (ball.getBallY() > HEIGHT || ball.getBallY() < 0 ||
                    ball.getBallX() > Dimension.WIDTH || ball.getBallX() < 0) {
                return true;
            }
        return false;
    }

    public boolean isLost() {
        return hasCollapseBall() || hasIntersectedBalls() || getMinutes() > 2 || secondPlayerWin();
    }

    private boolean secondPlayerWin() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (gameBoard.isTwosomeGame()) {
            for (int i = gameBoard.getBallNumber() + gameBoard.getDefaultBallNumber(); i < gameBoard.getAllBalls().size(); i++)
                if (!gameBoard.getAllBalls().get(i).isConnect())
                    return false;
            return true;
        } else
            return false;
    }

    private boolean hasIntersectedBalls() {
        for (Ball ball1 : DataBase.getCurrentGame().getAllBalls()) {
            if (ball1.isShot())
                for (Ball ball2 : DataBase.getCurrentGame().getAllBalls()) {
                    if (ball1.equals(ball2))
                        continue;
                    if (!ball2.isShot() && !ball2.isDefaultBall())
                        continue;
                    if (distanceBetweenTwoBalls(ball1, ball2) < ball1.getBallRadius() + ball2.getBallRadius()) {
                        return true;
                    }
                }
        }
        return false;
    }

    public Ball getBall(boolean forFirstOpponent) {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (forFirstOpponent) {
            for (int i = 0; i < gameBoard.getBallNumber(); i++)
                if (!gameBoard.getAllBalls().get(i).isShot() && gameBoard.getAllBalls().get(i).isReadyToLaunch())
                    return gameBoard.getAllBalls().get(i);
        } else {
            if (gameBoard.isTwosomeGame()) {
                for (int i = gameBoard.getBallNumber() + gameBoard.getDefaultBallNumber(); i < gameBoard.getAllBalls().size(); i++) {
                    if (!gameBoard.getAllBalls().get(i).isShot() && gameBoard.getAllBalls().get(i).isReadyToLaunch())
                        return gameBoard.getAllBalls().get(i);
                }
            }
        }
        return null;
    }

    public Ball setBallReadyToLaunch(boolean forFirstOpponent) {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (forFirstOpponent) {
            for (int i = 0; i < gameBoard.getBallNumber(); i++)
                if (!gameBoard.getAllBalls().get(i).isShot())
                    return gameBoard.getAllBalls().get(i);
        } else {
            if (gameBoard.isTwosomeGame()) {
                for (int i = gameBoard.getBallNumber() + gameBoard.getDefaultBallNumber(); i < gameBoard.getAllBalls().size(); i++) {
                    if (!gameBoard.getAllBalls().get(i).isShot())
                        return gameBoard.getAllBalls().get(i);
                }
            }
        }
        return null;
    }

    public void moveBalls() {
        for (Ball ball : DataBase.getCurrentGame().getAllBalls()) {
            if (ball.isShot() || ball.isDefaultBall()) {
                ball.ballMoving(getWindSpeed());
            }
        }
    }

    public void shootBall(boolean firstOpponentShoot) {
        if (!firstOpponentShoot && !DataBase.getCurrentGame().isTwosomeGame())
            return;
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (firstOpponentShoot)
            gameBoard.setFirstPlayerBallsRemain(gameBoard.getFirstPlayerBallsRemain() - 1);
        else
            gameBoard.setSecondPlayerBallsRemain(gameBoard.getSecondPlayerBallsRemain() - 1);

        gameBoard.addPlayerScore(firstOpponentShoot);
        gameBoard.getIceProgress().addIceProgressPercent();
        gameBoard.setBallsShot(gameBoard.getBallsShot() + 1);
        Ball thisBall = getBall(firstOpponentShoot);
        thisBall.shootBall(gameBoard.getMagicForceDegree());
        thisBall.setVisible(gameBoard.isVisibilityMode());
        if (firstOpponentShoot) {
            for (int i = 0; i < gameBoard.getBallNumber(); i++)
                if (!gameBoard.getAllBalls().get(i).isShot()) {
                    gameBoard.getAllBalls().get(i).setReadyToLaunch();
                    return;
                }
        } else {
            if (gameBoard.isTwosomeGame())
                for (int i = gameBoard.getBallNumber() + gameBoard.getDefaultBallNumber(); i < gameBoard.getAllBalls().size(); i++)
                    if (!gameBoard.getAllBalls().get(i).isShot()) {
                        gameBoard.getAllBalls().get(i).setReadyToLaunch();
                        return;
                    }
        }
    }

    public String getPlayerScore(boolean isFirstPlayer) {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (isFirstPlayer) {
            return String.valueOf(gameBoard.getFirstPlyerScore());
        } else
            return String.valueOf(gameBoard.getSecondPlayerScore());
    }

    public void addWinGamePrize() {
        GameBoard gameBoard = DataBase.getCurrentGame();
        if (isWin())
            gameBoard.addWinGamePrize(isWin());
        if (secondPlayerWin())
            gameBoard.addWinGamePrize(false);


        if (haveCurrentUser()) {
            DataBase.getCurrentUser().addToGames(new GameScore(isWin(), gameBoard.getFirstPlyerScore(),
                    gameBoard.getAllSeconds(), gameBoard.getDifficulty()));
            if (isTwoSomeGame())
                DataBase.getUserByUsername(gameBoard.getYourRivalUsername()).addToGames(new GameScore(secondPlayerWin(),
                        gameBoard.getSecondPlayerScore(), gameBoard.getAllSeconds(), gameBoard.getDifficulty()));
        }
    }

    public boolean haveCurrentUser() {
        if (DataBase.getCurrentUser() == null)
            return false;
        return true;
    }
}

