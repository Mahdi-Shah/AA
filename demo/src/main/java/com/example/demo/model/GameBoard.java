package com.example.demo.model;

import java.util.ArrayList;

import static com.example.demo.model.Dimension.HEIGHT;
import static com.example.demo.model.Dimension.WIDTH;

public class GameBoard {

    private final int ballNumber;
    private final Ball[] balls;
    private final CenterBall centerBall;
    private final Wind wind;
    private final Time timer;
    private int ballsShot;

    private double magicForceDegree;
    private final double ballRadius;
    private boolean twiceFazeBegins;
    private boolean thirdFazeBegins;
    private boolean fourthFazeBegins;
    private boolean visibilityMode;
    private int nextRotationSecond;
    private final double ballRotationSpeed;
    private final IceProgress iceProgress;



    public GameBoard(int ballNumber, double windRange, double stopDistance,
                     double ballSpeed, double ballRadius, double rotateSpeed, double bigCircleRadius, int iceProgressTime) {
        this.magicForceDegree = 0;
        this.ballNumber = ballNumber;
        this.wind = new Wind(windRange);
        this.timer = new Time();
        this.ballsShot = 0;
        this.ballRadius = ballRadius;
        this.twiceFazeBegins = this.thirdFazeBegins = this.fourthFazeBegins = false;
        this.visibilityMode = true;
        this.balls = new Ball[ballNumber];
        this.centerBall = new CenterBall(bigCircleRadius);
        this.nextRotationSecond = 0;
        this.ballRotationSpeed = rotateSpeed;
        this.iceProgress = new IceProgress(iceProgressTime, this);
        for (int i = 0; i < ballNumber; i++)
            balls[i] = new Ball(centerBall.getCircleCenterX(), centerBall.getCircleCenterY(),
                    stopDistance, ballRadius, ballSpeed, rotateSpeed, ballNumber - i);
        balls[0].setReadyToLaunch();
    }

    public double getWindSpeed() {
        return wind.getWindSpeed();
    }

    public Ball[] getBalls() {
        return balls;
    }

    public int getNextRotationSecond() {
        return nextRotationSecond;
    }

    public void setNextRotationSecond(int nextRotationSecond) {
        this.nextRotationSecond = nextRotationSecond;
    }

    public CenterBall getCenterBall() {
        return centerBall;
    }

    public Ball getBall() {
        for (Ball ball : balls)
            if (!ball.isShot() && ball.isReadyToLaunch())
                return ball;
        return null;
    }

    public void moveBalls() {
        for (Ball ball : balls)
            if (ball.isShot())
                ball.ballMoving(getWindSpeed());
    }

    private boolean hasIntersectedBalls() {
        for (Ball ball1 : balls) {
            if (ball1.isShot())
                for (Ball ball2 : balls) {
                    if (ball1.equals(ball2))
                        continue;
                    if (!ball2.isShot())
                        continue;
                    if (distanceBetweenTwoBalls(ball1, ball2) < ball1.getBallRadius() + ball2.getBallRadius())
                        return true;
                }
        }
        return false;
    }

    public void setBallsRadius(double ballRadius) {
        for (Ball ball : balls) {
            ball.setBallRadius(ballRadius);
        }
    }

    private double distanceBetweenTwoBalls(Ball ball1, Ball ball2) {
        return Math.sqrt((Math.pow((ball1.getBallX() - ball2.getBallX()), 2)) + (Math.pow((ball1.getBallY() - ball2.getBallY()), 2)));
    }

    private boolean hasCollapseBall() {
        for (Ball ball : balls)
            if (ball.getBallY() > HEIGHT || ball.getBallY() < 0 ||
                    ball.getBallX() > WIDTH || ball.getBallX() < 0) {
                return true;
            }
        return false;
    }

    public boolean isLost() {
        return hasCollapseBall() || hasIntersectedBalls() || getMinutes() > 2;
    }

    public boolean isWin() {
        for (Ball ball : balls)
            if (!ball.isConnect())
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
        for (Ball ball : balls) {
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
        return ballRadius;
    }

    public void shootBall() {
        iceProgress.addIceProgressPercent();
        ballsShot++;
        Ball thisBall = getBall();
        thisBall.shootBall(this.magicForceDegree);
        thisBall.setVisible(this.visibilityMode);
        for (Ball ball : balls)
            if (!ball.isShot()) {
                ball.setReadyToLaunch();
                return;
            }

    }

    public void getReverse() {
        for (Ball ball : balls) {
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
        for (Ball ball : balls) {
            if (ball.isShot())
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
        if (iceProgress.getIceProgressPercent() == 1)
            iceProgress.startIceProgress();
    }

    public void stopIceProgress() {
        iceProgress.stopIceProgress();
    }
}
