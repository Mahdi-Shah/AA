package com.example.demo.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.example.demo.model.Dimension.HEIGHT;
import static com.example.demo.model.Dimension.WIDTH;

public class Ball {

    private static final int BALL_RADIUS = 10;

    private static final double BALL_SPEED = 5.0;

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

    private final double stopDistance;

    private final double ballSpeed;

    private boolean shot;

    private boolean readyToLaunch;

    private final int number;

    private boolean visible;

    public Ball(double bigCircleX, double bigCircleY,
                double stopDistance, double ballRadius, double ballSpeed, double ballRotateSpeed, int number) {
        this.setVisible(false);
        this.bigCircleX = bigCircleX;
        this.bigCircleY = bigCircleY;
        this.stopDistance = stopDistance;
        this.ballRadius = ballRadius;
        this.ballSpeed = ballSpeed;
        this.ballRotateSpeed = ballRotateSpeed;
        this.shot = false;
        this.isConnect = false;
        this.readyToLaunch = false;
        this.number = number;
        this.visible = false;
    }

    private boolean isConnected() {
        if (Math.sqrt(Math.pow((ballX - bigCircleX), 2) + Math.pow((ballY - bigCircleY), 2)) <= stopDistance) {
            isConnect = true;
            return true;
        }
        return false;
    }

    public void ballMoving(double windSpeed) {
        if (!isConnect && !isConnected()) {
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
        ballVelocityX = ballSpeed * Math.sin(magicHandDegree / 180 * Math.PI);
        ballVelocityY = -ballSpeed * Math.cos(magicHandDegree / 180 * Math.PI);
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
            ballVelocityX = 0.0;
            ballVelocityY = -ballSpeed;
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
}
