package com.example.demo.model;

public class IceProgress {

    private static final double INCREASE_PERCENT = 0.3;
    private double iceProgressPercent;
    private final int iceProgressTime;
    private int whenIceProgressBegins;
    private boolean inIceProgressTime;
    GameBoard gameBoard;

    public IceProgress(int iceProgressTime, GameBoard gameBoard) {
        this.iceProgressTime = iceProgressTime;
        this.iceProgressPercent = 0;
        this.inIceProgressTime = false;
        this.gameBoard = gameBoard;
    }

    public double getIceProgressPercent() {
        return iceProgressPercent;
    }

    public void startIceProgress() {
        this.whenIceProgressBegins = gameBoard.getAllSeconds();
        iceProgressPercent = 0;
        gameBoard.setBallsIcySpeed();
        this.inIceProgressTime = true;
    }

    public void addIceProgressPercent() {
        this.iceProgressPercent += INCREASE_PERCENT;
        if (this.iceProgressPercent > 1)
            this.iceProgressPercent = 1;
    }

    public void stopIceProgress() {
        if (inIceProgressTime)
            if (gameBoard.getAllSeconds() - whenIceProgressBegins > this.iceProgressTime) {
                this.gameBoard.setBallsNormalSpeed();
                this.inIceProgressTime = false;
            }
    }
}
