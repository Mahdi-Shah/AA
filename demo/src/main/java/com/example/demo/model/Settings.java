package com.example.demo.model;

import javafx.scene.input.KeyCode;

public class Settings {
    private int ballNumber = 10;
    private double windRange = 1.5;
    private double rotateSpeed = 0.05;
    private int iceProgressTime = 4;
    private boolean blackAndWhite = false;
    private String language = "English";
    private boolean muteMenu = false;
    private boolean muteGame = false;
    private int gameSongNumber = 2;

    private KeyCode stopGameKeyCode = KeyCode.ESCAPE;
    private KeyCode goRightFirstOpponent = KeyCode.RIGHT;
    private KeyCode goLeftFirstOpponent = KeyCode.LEFT;
    private KeyCode goRightSecondOpponent = KeyCode.D;
    private KeyCode goLeftSecondOpponent = KeyCode.A;
    private KeyCode shootBallFirstOpponent = KeyCode.SPACE;
    private KeyCode shootBallSecondOpponent = KeyCode.ENTER;
    private KeyCode iceKey = KeyCode.TAB;

    public int getBallNumber() {
        return ballNumber;
    }

    public void setBallNumber(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    public double getWindRange() {
        return windRange;
    }

    public void setWindRange(double windRange) {
        this.windRange = windRange;
    }

    public double getRotateSpeed() {
        return rotateSpeed;
    }

    public void setRotateSpeed(double rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    public int getIceProgressTime() {
        return iceProgressTime;
    }

    public void setIceProgressTime(int iceProgressTime) {
        this.iceProgressTime = iceProgressTime;
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isMuteMenu() {
        return muteMenu;
    }

    public void setMuteMenu(boolean muteMenu) {
        this.muteMenu = muteMenu;
    }

    public boolean isMuteGame() {
        return muteGame;
    }

    public void setMuteGame(boolean muteGame) {
        this.muteGame = muteGame;
    }

    public KeyCode getStopGameKeyCode() {
        return stopGameKeyCode;
    }

    public void setStopGameKeyCode(KeyCode stopGameKeyCode) {
        this.stopGameKeyCode = stopGameKeyCode;
    }

    public KeyCode getGoRightFirstOpponent() {
        return goRightFirstOpponent;
    }

    public void setGoRightFirstOpponent(KeyCode goRightFirstOpponent) {
        this.goRightFirstOpponent = goRightFirstOpponent;
    }

    public KeyCode getGoLeftFirstOpponent() {
        return goLeftFirstOpponent;
    }

    public void setGoLeftFirstOpponent(KeyCode goLeftFirstOpponent) {
        this.goLeftFirstOpponent = goLeftFirstOpponent;
    }

    public KeyCode getGoRightSecondOpponent() {
        return goRightSecondOpponent;
    }

    public void setGoRightSecondOpponent(KeyCode goRightSecondOpponent) {
        this.goRightSecondOpponent = goRightSecondOpponent;
    }

    public KeyCode getGoLeftSecondOpponent() {
        return goLeftSecondOpponent;
    }

    public void setGoLeftSecondOpponent(KeyCode goLeftSecondOpponent) {
        this.goLeftSecondOpponent = goLeftSecondOpponent;
    }

    public KeyCode getShootBallFirstOpponent() {
        return shootBallFirstOpponent;
    }

    public void setShootBallFirstOpponent(KeyCode shootBallFirstOpponent) {
        this.shootBallFirstOpponent = shootBallFirstOpponent;
    }

    public KeyCode getShootBallSecondOpponent() {
        return shootBallSecondOpponent;
    }

    public void setShootBallSecondOpponent(KeyCode shootBallSecondOpponent) {
        this.shootBallSecondOpponent = shootBallSecondOpponent;
    }

    public KeyCode getIceKey() {
        return iceKey;
    }

    public void setIceKey(KeyCode iceKey) {
        this.iceKey = iceKey;
    }

    public int getGameSongNumber() {
        return gameSongNumber;
    }

    public void setGameSongNumber(int gameSongNumber) {
        this.gameSongNumber = gameSongNumber;
    }
}
