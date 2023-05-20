package com.example.demo.model;



import static com.example.demo.model.Dimension.HEIGHT;
import static com.example.demo.model.Dimension.WIDTH;

public class CenterBall {

    private static final int RADIUS = 150;

    private final double circleCenterX;
    private final double circleCenterY;

    private final double radius;

    public CenterBall(double radius) {
        this.circleCenterX = WIDTH / 2;
        this.circleCenterY = HEIGHT / 2;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getCircleCenterX() {
        return circleCenterX;
    }

    public double getCircleCenterY() {
        return circleCenterY;
    }
}
