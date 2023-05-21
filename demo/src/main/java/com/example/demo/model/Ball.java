package com.example.demo.model;

import javafx.scene.paint.Color;

import static com.example.demo.model.Dimension.*;

public class Ball {

    private static final double BALL_ROTATE_SPEED = 0.05;

    private double ballRotateSpeed;
    private double ballRadius;
    private double ballX;
    private double ballY;
    private double ballVelocityX;
    private double ballVelocityY;

    private boolean isConnect;

    private final double bigCircleX;

    private final double bigCircleY;

    private boolean shot;

    private boolean readyToLaunch;

    private final int number;

    private boolean visible;
    private final boolean defaultBall;
    private final boolean forFirstOpponent;
    private final double stopDistance;

    public Ball(double bigCircleX, double bigCircleY,
                double ballRotateSpeed, int number, boolean forFirstOpponent, double stopDistance) {
        this.setVisible(false);
        this.bigCircleX = bigCircleX;
        this.bigCircleY = bigCircleY;
        this.ballRotateSpeed = ballRotateSpeed;
        this.shot = false;
        this.isConnect = false;
        this.readyToLaunch = false;
        this.number = number;
        this.visible = false;
        this.ballRadius = BALL_RADIUS;
        this.defaultBall = false;
        this.forFirstOpponent = forFirstOpponent;
        this.stopDistance = stopDistance;
    }

    public Ball(double bigCircleX, double bigCircleY, int numBalls, int ballNumber, double ballRotateSpeed, double stopDistance, Color color) {
        double angle = Math.toRadians(360.0 / numBalls * ballNumber);
        this.ballRadius = BALL_RADIUS;
        this.bigCircleX = bigCircleX;
        this.bigCircleY = bigCircleY;
        this.ballX = bigCircleX + stopDistance * Math.cos(angle);
        this.ballY = bigCircleY + stopDistance * Math.sin(angle);
        this.shot = true;
        this.visible = true;
        this.number = ballNumber;
        this.ballRotateSpeed = ballRotateSpeed;
        this.defaultBall = true;
        this.forFirstOpponent = true;
        this.stopDistance = stopDistance;
    }

    private boolean isConnected() {
        if (Math.sqrt(Math.pow((ballX - bigCircleX), 2) + Math.pow((ballY - bigCircleY), 2)) <= stopDistance + BALL_RADIUS) {
            isConnect = true;
            return true;
        }
        return false;
    }

    public void ballMoving(double windSpeed) {
        if (!isConnect && !isConnected() && !isDefaultBall()) {
            ballX += ballVelocityX + windSpeed;
            ballY += ballVelocityY;
        } else {
            double x = ballX - bigCircleX;
            double y = ballY - bigCircleY;

            double newX = x * Math.cos(ballRotateSpeed) - y * Math.sin(ballRotateSpeed);
            double newY = x * Math.sin(ballRotateSpeed) + y * Math.cos(ballRotateSpeed);

            this.ballX = newX + bigCircleX;
            this.ballY = newY + bigCircleY;

        }
    }

    public void shootBall(double magicHandDegree) {
        this.shot = true;
        ballVelocityX = BALL_SPEED * Math.sin(magicHandDegree * 2 / 360);
        if (forFirstOpponent) {
            ballVelocityY = -BALL_SPEED * Math.cos(magicHandDegree * 2 / 360);
        }
        else {
            ballVelocityY = BALL_SPEED * Math.cos(magicHandDegree * 2 / 360);
        }
        this.setVisible(true);
    }

    public boolean isShot() {
        return shot;
    }

    public double getBallRadius() {
        return ballRadius;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public boolean isReadyToLaunch() {
        return readyToLaunch;
    }

    public void setReadyToLaunch() {
        if (!isReadyToLaunch()) {
            ballX = WIDTH / 2.0;
            if (forFirstOpponent)
                ballY = HEIGHT - ballRadius - 80;
            else
                ballY = ballRadius + 80;
            this.setVisible(true);
            this.readyToLaunch = true;
        }
    }

    public void setBallRotateSpeed(double ballRotateSpeed) {
        this.ballRotateSpeed = ballRotateSpeed;
    }

    public void setBallRadius(double ballRadius) {
        this.ballRadius = ballRadius;
    }

    public double getBallRotateSpeed() {
        return ballRotateSpeed;
    }

    public int getNumber() {
        return number;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void moveToLeft(boolean toLeft) {
        double isToLeft = 10;
        if (toLeft)
            isToLeft = -10;
        this.ballX += isToLeft;
    }

    public boolean isDefaultBall() {
        return defaultBall;
    }

    public boolean isForFirstOpponent() {
        return forFirstOpponent;
    }

    public String toString() {
        return "isShot:" + isShot() + " isConnect:" + isConnect + " number:" + getNumber() + " default:" + isDefaultBall() + " first:" + isForFirstOpponent() + " ballX:" + ballX;
    }

    public void setVelocityX(double v) {
        this.ballVelocityX = v;
    }

    public void setVelocityY(double v) {
        this.ballVelocityY = v;
    }

    public void setBallX(double ballX) {
        this.ballX = ballX;
    }

    public void setBallY(double ballY) {
        this.ballY = ballY;
    }
}
