package com.example.demo.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.example.demo.model.Dimension.*;

public class GameBoard{

    private int firstPlayerBallsRemain;
    private int secondPlayerBallsRemain;

    private final ArrayList<Ball> allBalls;

    private final int ballNumber;

    private final int defaultBallNumber;
    private final boolean isTwosomeGame;
    private final CenterBall centerBall;
    private final Wind wind;
    private final Time timer;
    private int ballsShot;

    private double magicForceDegree;
    private boolean twiceFazeBegins;
    private boolean thirdFazeBegins;
    private boolean fourthFazeBegins;
    private boolean visibilityMode;
    private int nextRotationSecond;
    private final double ballRotationSpeed;
    private final IceProgress iceProgress;
    private final String yourRivalUsername;
    private final int difficulty;
    private int firstPlyerScore;
    private int secondPlayerScore;
    private boolean gameOver;
    private boolean win;
    private boolean lose;


    public GameBoard(int ballNumber, double windRange, double rotateSpeed,
                     int iceProgressTime, int numberOfDefaultBalls, boolean isTwosomeGame,
                     double stopDistance, String yourRivalUsername) {
        this.magicForceDegree = 0;
        this.ballNumber = ballNumber;
        this.wind = new Wind(windRange);
        this.timer = new Time();
        this.ballsShot = 0;
        this.twiceFazeBegins = this.thirdFazeBegins = this.fourthFazeBegins = false;
        this.visibilityMode = true;
        this.centerBall = new CenterBall();
        this.nextRotationSecond = 0;
        this.ballRotationSpeed = rotateSpeed;
        this.iceProgress = new IceProgress(iceProgressTime);
        this.allBalls = new ArrayList<>();
        this.isTwosomeGame = isTwosomeGame;
        this.defaultBallNumber = numberOfDefaultBalls;
        this.firstPlayerBallsRemain = this.secondPlayerBallsRemain = ballNumber;
        this.yourRivalUsername = yourRivalUsername;
        this.gameOver = false;
        this.win = false;
        this.lose = false;


        if (rotateSpeed >= 0.11 || ballNumber > 30) {
            this.difficulty = 3;
        } else if (rotateSpeed >= 0.07 || ballNumber > 15) {
            this.difficulty = 2;
        } else
            this.difficulty = 1;

        this.firstPlyerScore = 0;
        this.secondPlayerScore = 0;

        for (int i = 0; i < ballNumber; i++) {
            allBalls.add(new Ball(centerBall.getCircleCenterX(), centerBall.getCircleCenterY(),
                    rotateSpeed, ballNumber - i, true, stopDistance));
        }
        allBalls.get(0).setReadyToLaunch();


        for (int i = 0; i < numberOfDefaultBalls; i++) {
            allBalls.add(new Ball(centerBall.getCircleCenterX(), centerBall.getCircleCenterY(),
                    numberOfDefaultBalls, i, rotateSpeed, stopDistance, Color.BLACK));
        }



        if (isTwosomeGame) {
            for (int i = 0; i < ballNumber; i++) {
                allBalls.add(new Ball(centerBall.getCircleCenterX(), centerBall.getCircleCenterY(),
                        rotateSpeed, ballNumber - i, false, stopDistance));
            }
            allBalls.get(ballNumber + defaultBallNumber).setReadyToLaunch();
        }
    }

    public CenterBall getCenterBall() {
        return centerBall;
    }

    public double getWindSpeed() {
        return wind.getWindSpeed();
    }

    public int getNextRotationSecond() {
        return nextRotationSecond;
    }

    public void setNextRotationSecond(int nextRotationSecond) {
        this.nextRotationSecond = nextRotationSecond;
    }

    public void setBallsRadius(double ballRadius) {
        for (Ball ball : allBalls) {
            ball.setBallRadius(ballRadius);
        }
    }

    public double getMagicForceDegree() {
        return magicForceDegree;
    }

    public void setMagicDegree(double magicForceDegree) {
        this.magicForceDegree = magicForceDegree;
    }

    public double getIceProgressPercent() {
        return iceProgress.getIceProgressPercent();
    }

    private void setBallsSpeed(double v) {
        for (Ball ball : allBalls) {
            ball.setBallRotateSpeed(v);
        }
    }

    public void setTimer(long now) {
        timer.setTimer(now);
    }

    public void stopTimer() {
        timer.stopTimer();
    }

    public int getAllSeconds() {
        return timer.getAllSeconds();
    }

    public int getSeconds() {
        return timer.getSeconds();
    }

    public int getMinutes() {
        return timer.getMinutes();
    }

    public Double getAllSecondsDouble() {
        return timer.getAllSecondsDouble();
    }

    public double getBallRadius() {
        return BALL_RADIUS;
    }

    public void getReverse() {
        for (Ball ball : allBalls) {
            ball.setBallRotateSpeed(ball.getBallRotateSpeed() * -1);
        }
    }

    public boolean isTwiceFazeBegins() {
        if (ballsShot > 0)
            if (twiceFazeBegins || ballNumber / ballsShot <= 4)
                twiceFazeBegins = true;
        return twiceFazeBegins;
    }

    public boolean isThirdFazeBegins() {
        if (ballsShot > 0)
            if (thirdFazeBegins || ballNumber / ballsShot <= 2)
                thirdFazeBegins = true;
        return thirdFazeBegins;
    }

    public boolean isFourthFazeBegins() {
        if (ballsShot > 0)
            if (fourthFazeBegins || ballNumber * 3 / ballsShot <= 4)
                fourthFazeBegins = true;
        return fourthFazeBegins;
    }

    public void setBallsIcySpeed() {
        setBallsSpeed(ballRotationSpeed / 2);
    }

    public void setBallsNormalSpeed() {
        setBallsSpeed(ballRotationSpeed);
    }

    public boolean isTwosomeGame() {
        return isTwosomeGame;
    }

    public ArrayList<Ball> getAllBalls() {
        return allBalls;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public int getDefaultBallNumber() {
        return defaultBallNumber;
    }

    public int getFirstPlayerBallsRemain() {
        return firstPlayerBallsRemain;
    }

    public int getSecondPlayerBallsRemain() {
        return secondPlayerBallsRemain;
    }

    public void setFirstPlayerBallsRemain(int firstPlayerBallsRemain) {
        this.firstPlayerBallsRemain = firstPlayerBallsRemain;
    }

    public void setSecondPlayerBallsRemain(int secondPlayerBallsRemain) {
        this.secondPlayerBallsRemain = secondPlayerBallsRemain;
    }

    public int getBallsShot() {
        return ballsShot;
    }

    public void setBallsShot(int ballsShot) {
        this.ballsShot = ballsShot;
    }

    public boolean isVisibilityMode() {
        return visibilityMode;
    }

    public void setVisibilityMode(boolean visibilityMode) {
        this.visibilityMode = visibilityMode;
    }

    public IceProgress getIceProgress() {
        return iceProgress;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getFirstPlyerScore() {
        return firstPlyerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public void addPlayerScore(boolean isFirstPlayer) {
        int variable;
        if (fourthFazeBegins)
            variable = 80;
        else if (thirdFazeBegins)
            variable = 40;
        else if (twiceFazeBegins)
            variable = 20;
        else
            variable = 10;
        if (isFirstPlayer)
            firstPlyerScore += difficulty * variable;
        else
            secondPlayerScore += difficulty * variable;
    }

    public void addWinGamePrize(boolean isFirstPlayer) {
        if (isFirstPlayer)
            firstPlyerScore += difficulty * 100;
        else
            secondPlayerScore += difficulty * 100;
    }

    public String getYourRivalUsername() {
        return yourRivalUsername;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }
}
