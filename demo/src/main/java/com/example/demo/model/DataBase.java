package com.example.demo.model;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demo.view.RegisterMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.input.KeyCode;

public class DataBase {
    private static ArrayList<User> users = new ArrayList<>();

    private static Settings settings = new Settings();

    private static int ballNumber = 10;
    private static double windRange = 1.5;
    private static double rotateSpeed = 0.05;
    private static int iceProgressTime = 4;
    private static int numberOfDefaultBalls = 5;
    private static boolean isTwosomeGame = false;
    private static double stopDistance = 180;
    private static boolean blackAndWhite = false;
    private static String rivalUsername = null;
    private static String language = "English";
    private static boolean muteMenu = false;
    private static boolean muteGame = false;
    private static int gameSongNumber = 2;

    private static KeyCode stopGameKeyCode = KeyCode.ESCAPE;
    private static KeyCode goRightFirstOpponent = KeyCode.RIGHT;
    private static KeyCode goLeftFirstOpponent = KeyCode.LEFT;
    private static KeyCode goRightSecondOpponent = KeyCode.D;
    private static KeyCode goLeftSecondOpponent = KeyCode.A;
    private static KeyCode shootBallFirstOpponent = KeyCode.SPACE;
    private static KeyCode shootBallSecondOpponent = KeyCode.ENTER;
    private static KeyCode iceKey = KeyCode.TAB;

    private static GameBoard currentGame = new GameBoard(ballNumber, windRange,rotateSpeed, iceProgressTime, numberOfDefaultBalls, isTwosomeGame, stopDistance, rivalUsername);

    private static User currentUser;

    public static void readDataFromBase() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(Objects.requireNonNull(RegisterMenu.class.getResource(
                "/com/example/demo/usersdatabase/users.json")).getFile());
        List<User> loadedUser = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        reader.close();
        if (loadedUser != null && !loadedUser.isEmpty())
            users.addAll(loadedUser);
    }

    public static void writeDataToBase() throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(users);
        FileWriter writer = new FileWriter(Objects.requireNonNull(RegisterMenu.class.getResource("/com/example/demo/usersdatabase/users.json")).getFile());
        writer.write(json);
        writer.close();
    }

    public static User getUserByUsername(String username) {
        for (User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        DataBase.currentUser = currentUser;
    }

    public static GameBoard getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame() {
        DataBase.currentGame = new GameBoard(ballNumber, windRange, rotateSpeed, iceProgressTime, numberOfDefaultBalls, isTwosomeGame, stopDistance, rivalUsername);
    }

    public static void addUser(User user) throws IOException {
        users.add(user);
        writeDataToBase();
    }

    public static void setBallNumber(int ballNumber) {
        DataBase.ballNumber = ballNumber;
        settings.setBallNumber(ballNumber);
    }

    public static void setWindRange(double windRange) {
        DataBase.windRange = windRange;
        settings.setWindRange(windRange);
    }

    public static void setRotateSpeed(double rotateSpeed) {
        DataBase.rotateSpeed = rotateSpeed;
        settings.setRotateSpeed(rotateSpeed);
    }

    public static void setIceProgressTime(int iceProgressTime) {
        DataBase.iceProgressTime = iceProgressTime;
        settings.setIceProgressTime(iceProgressTime);
    }

    public static void setNumberOfDefaultBalls(int numberOfDefaultBalls) {
        DataBase.numberOfDefaultBalls = numberOfDefaultBalls;
    }

    public static void setIsTwosomeGame(boolean isTwosomeGame) {
        DataBase.isTwosomeGame = isTwosomeGame;
    }

    public static void setStopDistance(double stopDistance) {
        DataBase.stopDistance = stopDistance;
    }

    public static void setBlackAndWhite(boolean blackAndWhite) {
        DataBase.blackAndWhite = blackAndWhite;
        settings.setBlackAndWhite(blackAndWhite);
    }

    public static void setCurrentGame(GameBoard currentGame) {
        DataBase.currentGame = currentGame;
    }

    public static boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public static KeyCode getStopGameKeyCode() {
        return stopGameKeyCode;
    }

    public static void setStopGameKeyCode(KeyCode stopGameKeyCode) {
        DataBase.stopGameKeyCode = stopGameKeyCode;
        settings.setStopGameKeyCode(stopGameKeyCode);
    }

    public static KeyCode getGoRightFirstOpponent() {
        return goRightFirstOpponent;
    }

    public static void setGoRightFirstOpponent(KeyCode goRightFirstOpponent) {
        DataBase.goRightFirstOpponent = goRightFirstOpponent;
        settings.setGoRightFirstOpponent(goRightFirstOpponent);
    }

    public static KeyCode getGoLeftFirstOpponent() {
        return goLeftFirstOpponent;
    }

    public static void setGoLeftFirstOpponent(KeyCode goLeftFirstOpponent) {
        DataBase.goLeftFirstOpponent = goLeftFirstOpponent;
        settings.setGoLeftFirstOpponent(goLeftFirstOpponent);
    }

    public static KeyCode getGoRightSecondOpponent() {
        return goRightSecondOpponent;
    }

    public static void setGoRightSecondOpponent(KeyCode goRightSecondOpponent) {
        DataBase.goRightSecondOpponent = goRightSecondOpponent;
        settings.setGoRightSecondOpponent(goRightSecondOpponent);
    }

    public static KeyCode getGoLeftSecondOpponent() {
        return goLeftSecondOpponent;
    }

    public static void setGoLeftSecondOpponent(KeyCode goLeftSecondOpponent) {
        DataBase.goLeftSecondOpponent = goLeftSecondOpponent;
        settings.setGoLeftSecondOpponent(goLeftSecondOpponent);
    }

    public static KeyCode getShootBallFirstOpponent() {
        return shootBallFirstOpponent;
    }

    public static void setShootBallFirstOpponent(KeyCode shootBallFirstOpponent) {
        DataBase.shootBallFirstOpponent = shootBallFirstOpponent;
        settings.setShootBallFirstOpponent(shootBallFirstOpponent);
    }

    public static KeyCode getShootBallSecondOpponent() {
        return shootBallSecondOpponent;
    }

    public static void setShootBallSecondOpponent(KeyCode shootBallSecondOpponent) {
        DataBase.shootBallSecondOpponent = shootBallSecondOpponent;
        settings.setShootBallSecondOpponent(shootBallSecondOpponent);
    }

    public static KeyCode getIceKey() {
        return iceKey;
    }

    public static void setIceKey(KeyCode iceKey) {
        DataBase.iceKey = iceKey;
        settings.setIceKey(iceKey);
    }

    public static void setRivalUsername(String rivalUsername) {
        DataBase.rivalUsername = rivalUsername;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        DataBase.language = language;
        settings.setLanguage(language);
    }

    public static boolean isMuteMenu() {
        return muteMenu;
    }

    public static void setMuteMenu(boolean muteMenu) {
        DataBase.muteMenu = muteMenu;
        settings.setMuteMenu(muteMenu);
    }

    public static boolean isMuteGame() {
        return muteGame;
    }

    public static int getGameSongNumber() {
        return gameSongNumber;
    }

    public static void setGameSongNumber(int gameSongNumber) {
        DataBase.gameSongNumber = gameSongNumber;
        settings.setGameSongNumber(gameSongNumber);
    }

    public static void setMuteGame(boolean muteGame) {
        DataBase.muteGame = muteGame;
        settings.setMuteGame(muteGame);
    }

    public static void readSettings() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(Objects.requireNonNull(RegisterMenu.class.getResource(
                "/com/example/demo/usersdatabase/settings.json")).getFile());
        Settings setting = gson.fromJson(reader, new TypeToken<Settings>() {}.getType());
        reader.close();
        if (setting == null)
            return;
        setBallNumber(setting.getBallNumber());
        setWindRange(setting.getWindRange());
        setRotateSpeed(setting.getRotateSpeed());
        setIceProgressTime(setting.getIceProgressTime());
        setBlackAndWhite(setting.isBlackAndWhite());
        setLanguage(setting.getLanguage());
        setMuteMenu(setting.isMuteMenu());
        setMuteGame(setting.isMuteGame());
        setGameSongNumber(setting.getGameSongNumber());

        setStopGameKeyCode(setting.getStopGameKeyCode());
        setGoRightFirstOpponent(setting.getGoRightFirstOpponent());
        setGoLeftFirstOpponent(setting.getGoLeftFirstOpponent());
        setGoRightSecondOpponent(setting.getGoRightSecondOpponent());
        setGoLeftSecondOpponent(setting.getGoLeftSecondOpponent());
        setShootBallFirstOpponent(setting.getShootBallFirstOpponent());
        setShootBallSecondOpponent(setting.getShootBallSecondOpponent());
        setIceKey(setting.getIceKey());
    }

    public static void saveSettings() throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(settings);
        FileWriter writer = new FileWriter(Objects.requireNonNull(RegisterMenu.class.getResource("/com/example/demo/usersdatabase/settings.json")).getFile());
        writer.write(json);
        writer.close();
    }

}
