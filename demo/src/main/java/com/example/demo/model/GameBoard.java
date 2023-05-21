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


    public GameBoard(int ballNumber, double windRange, double rotateSpeed,
                     int iceProgressTime, int numberOfDefaultBalls, boolean isTwosomeGame, double stopDistance) {
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

    public Ball getBall(boolean forFirstOpponent) {
        if (forFirstOpponent) {
            for (int i = 0; i < ballNumber; i++)
                if (!allBalls.get(i).isShot() && allBalls.get(i).isReadyToLaunch())
                    return allBalls.get(i);
        } else {
            if (isTwosomeGame) {
                for (int i = ballNumber + defaultBallNumber; i < allBalls.size(); i++) {
                    if (!allBalls.get(i).isShot() && allBalls.get(i).isReadyToLaunch())
                        return allBalls.get(i);
                }
            }
        }
        return null;
    }

    public Ball setBallReadyToLaunch(boolean forFirstOpponent) {
        if (forFirstOpponent) {
            for (int i = 0; i < ballNumber; i++)
                if (!allBalls.get(i).isShot())
                    return allBalls.get(i);
        } else {
            if (isTwosomeGame) {
                for (int i = ballNumber + defaultBallNumber; i < allBalls.size(); i++) {
                    if (!allBalls.get(i).isShot())
                        return allBalls.get(i);
                }
            }
        }
        return null;
    }

    public void moveBalls() {
        for (Ball ball : allBalls) {
            if (ball.isShot() || ball.isDefaultBall()) {
                ball.ballMoving(getWindSpeed());
            }
        }
    }

    private boolean hasIntersectedBalls() {
        for (Ball ball1 : allBalls) {
            if (ball1.isShot())
                for (Ball ball2 : allBalls) {
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

    public void setBallsRadius(double ballRadius) {
        for (Ball ball : allBalls) {
            ball.setBallRadius(ballRadius);
        }
    }

    private double distanceBetweenTwoBalls(Ball ball1, Ball ball2) {
        return Math.sqrt((Math.pow((ball1.getBallX() - ball2.getBallX()), 2)) + (Math.pow((ball1.getBallY() - ball2.getBallY()), 2)));
    }

    private boolean hasCollapseBall() {
        for (Ball ball : allBalls)
            if (ball.getBallY() > HEIGHT || ball.getBallY() < 0 ||
                    ball.getBallX() > WIDTH || ball.getBallX() < 0) {
                return true;
            }
        return false;
    }

    public boolean isLost() {
        return hasCollapseBall() || hasIntersectedBalls() || getMinutes() > 2 || secondPlayerWin();
    }

    private boolean secondPlayerWin() {
        if (isTwosomeGame)
            for (int i = ballNumber + defaultBallNumber; i < allBalls.size(); i++)
                if (!allBalls.get(i).isConnect())
                    return false;
        return true;
    }

    public boolean isWin() {
        for (int i = 0; i < ballNumber; i++)
            if (!allBalls.get(i).isConnect())
                return false;
        return true;
    }

    public boolean isGameOver() {
        if (isLost() || isWin()) {
            setVisible(true);
            return true;
        }
        return false;
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

    public void shootBall(boolean firstOpponentShoot) {
        if (firstOpponentShoot)
            firstPlayerBallsRemain--;
        else
            secondPlayerBallsRemain--;
        iceProgress.addIceProgressPercent();
        ballsShot++;
        Ball thisBall = getBall(firstOpponentShoot);
        thisBall.shootBall(this.magicForceDegree);
        thisBall.setVisible(this.visibilityMode);
        if (firstOpponentShoot) {
            for (int i = 0; i < ballNumber; i++)
                if (!allBalls.get(i).isShot()) {
                    allBalls.get(i).setReadyToLaunch();
                    return;
                }
        } else {
            if (isTwosomeGame)
                for (int i = ballNumber + defaultBallNumber; i < allBalls.size(); i++)
                    if (!allBalls.get(i).isShot()) {
                        allBalls.get(i).setReadyToLaunch();
                        return;
                    }
        }
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

    public void setVisible(boolean visibility) {
        this.visibilityMode = visibility;
        for (Ball ball : allBalls) {
            if (ball.isShot() || ball.isDefaultBall())
                ball.setVisible(visibility);
        }
    }

    public void setBallsIcySpeed() {
        setBallsSpeed(ballRotationSpeed / 2);
    }

    public void setBallsNormalSpeed() {
        setBallsSpeed(ballRotationSpeed);
    }

    public void startIceProgress() {
        if (iceProgress.getIceProgressPercent() == 1) {
            iceProgress.setWhenIceProgressBegins(this.getAllSeconds());
            iceProgress.setIceProgressPercent(0);
            this.setBallsIcySpeed();
            iceProgress.setInIceProgressTime(true);
        }
    }

    public void stopIceProgress() {
        if (iceProgress.isInIceProgressTime())
            if (this.getAllSeconds() - iceProgress.getWhenIceProgressBegins() > iceProgress.getIceProgressTime()) {
                this.setBallsNormalSpeed();
                iceProgress.setInIceProgressTime(false);
            }
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
}
