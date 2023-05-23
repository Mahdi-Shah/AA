package com.example.demo.model;

public class Time {

    private boolean flagForTime;
    private long firstTime;
    private double lastTime;

    private double allSecondsDouble;

    private int allSeconds;

    public Time() {
        flagForTime = true;
        lastTime = 0;
        allSeconds = 0;
    }

    public void setTimer(long now) {
        if (flagForTime) {
            flagForTime = false;
            firstTime = now;
        }
        double garbageTime = (now - firstTime);
        allSecondsDouble = garbageTime / 1000000000 + lastTime;
        allSeconds = ((int) allSecondsDouble);
    }

    public void stopTimer() {
        lastTime = allSecondsDouble;
        flagForTime = true;
    }

    public int getAllSeconds() {
        return allSeconds;
    }

    public int getSeconds() {

        return allSeconds % 60;
    }

    public int getMinutes() {
        return allSeconds / 60;
    }

    public double getAllSecondsDouble() {
        return allSecondsDouble;
    }


}
