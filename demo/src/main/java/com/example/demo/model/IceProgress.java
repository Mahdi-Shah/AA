package com.example.demo.model;

public class IceProgress {

    private static final double INCREASE_PERCENT = 0.3;
    private double iceProgressPercent;
    private final int iceProgressTime;
    private int whenIceProgressBegins;
    private boolean inIceProgressTime;

    public IceProgress(int iceProgressTime) {
        this.iceProgressTime = iceProgressTime;
        this.iceProgressPercent = 0;
        this.inIceProgressTime = false;
    }

    public double getIceProgressPercent() {
        return iceProgressPercent;
    }

    public void addIceProgressPercent() {
        this.iceProgressPercent += INCREASE_PERCENT;
        if (this.iceProgressPercent > 1)
            this.iceProgressPercent = 1;
    }

    public void setIceProgressPercent(double iceProgressPercent) {
        this.iceProgressPercent = iceProgressPercent;
    }

    public int getIceProgressTime() {
        return iceProgressTime;
    }

    public int getWhenIceProgressBegins() {
        return whenIceProgressBegins;
    }

    public void setWhenIceProgressBegins(int whenIceProgressBegins) {
        this.whenIceProgressBegins = whenIceProgressBegins;
    }

    public boolean isInIceProgressTime() {
        return inIceProgressTime;
    }

    public void setInIceProgressTime(boolean inIceProgressTime) {
        this.inIceProgressTime = inIceProgressTime;
    }
}
