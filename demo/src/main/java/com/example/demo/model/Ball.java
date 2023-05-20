package com.example.demo.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    public Ball(double bigCircleX, double bigCircleY,
                double ballRotateSpeed, int number) {
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
    }

    public Ball(double bigCircleX, double bigCircleY, int numBalls, int ballNumber, double ballRotateSpeed) {
        double angle = Math.toRadians(360.0 / numBalls * ballNumber);
        this.ballRadius = BALL_RADIUS;
        this.bigCircleX = bigCircleX;
        this.bigCircleY = bigCircleY;
        this.ballX = bigCircleX + STOP_DISTANCE * Math.cos(angle);
        this.ballY = bigCircleY + STOP_DISTANCE * Math.sin(angle);
        this.shot = false;
        this.visible = true;
        this.number = ballNumber;
        this.ballRotateSpeed = ballRotateSpeed;
        this.defaultBall = true;
    }

    private boolean isConnected() {
        if (Math.sqrt(Math.pow((ballX - bigCircleX), 2) + Math.pow((ballY - bigCircleY), 2)) <= STOP_DISTANCE) {
            isConnect = true;
            return true;
        }
        return false;
    }

    public void ballMoving(double windSpeed) {
        if (!isConnect && !isConnected() && !isDefaultBall()) {
            ballVelocityY += 0.01; // Add gravity
            ballX += ballVelocityX + windSpeed;
            ballY += ballVelocityY;
        } else {
            double x = ballX - bigCircleX;
            double y = ballY - bigCircleY;

            double newX = x * Math.cos(ballRotateSpeed) - y * Math.sin(ballRotateSpeed);
            double newY = x * Math.sin(ballRotateSpeed) + y * Math.cos(ballRotateSpeed);

            ballX = newX + bigCircleX;
            ballY = newY + bigCircleY;

        }
    }

    public void shootBall(double magicHandDegree) {
        this.shot = true;
        ballVelocityX = BALL_SPEED * Math.sin(magicHandDegree / 180 * Math.PI);
        ballVelocityY = -BALL_SPEED * Math.cos(magicHandDegree / 180 * Math.PI);
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
            ballY = HEIGHT - ballRadius;
            //todo: DELETE THIS TWO
            ballVelocityX = 0.0;
            ballVelocityY = -BALL_SPEED;
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
}
