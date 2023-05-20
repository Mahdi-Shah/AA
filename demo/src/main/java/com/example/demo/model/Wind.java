package com.example.demo.model;

public class Wind {
    private static final double WIND_SPEED = 2.0;

    private final double windSpeedRange;

    private double lastWindSpeed = 0;
    public Wind(double windSpeed) {
        this.windSpeedRange = windSpeed;
    }

    public double getWindSpeed() {
        if (Math.random() < 0.01)
            lastWindSpeed = Math.random() * windSpeedRange * 2 - windSpeedRange;
        return lastWindSpeed;
    }
}
