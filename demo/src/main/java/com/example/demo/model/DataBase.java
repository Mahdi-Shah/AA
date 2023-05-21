package com.example.demo.model;


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

    private static int ballNumber = 10;
    private static double windRange = 1.5;
    private static double rotateSpeed = 0.05;
    private static int iceProgressTime = 5;
    private static int numberOfDefaultBalls = 5;
    private static boolean isTwosomeGame = false;
    private static double stopDistance = 180;
    private static boolean blackAndWhite = false;
    private static String rivalUsername = null;

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
        FileReader reader = new FileReader(Objects.requireNonNull(RegisterMenu.class.getResource("/com/example/demo/usersdatabase/users.json")).getFile());
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
    }

    public static void setWindRange(double windRange) {
        DataBase.windRange = windRange;
    }

    public static void setRotateSpeed(double rotateSpeed) {
        DataBase.rotateSpeed = rotateSpeed;
    }

    public static void setIceProgressTime(int iceProgressTime) {
        DataBase.iceProgressTime = iceProgressTime;
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
    }

    public static KeyCode getGoRightFirstOpponent() {
        return goRightFirstOpponent;
    }

    public static void setGoRightFirstOpponent(KeyCode goRightFirstOpponent) {
        DataBase.goRightFirstOpponent = goRightFirstOpponent;
    }

    public static KeyCode getGoLeftFirstOpponent() {
        return goLeftFirstOpponent;
    }

    public static void setGoLeftFirstOpponent(KeyCode goLeftFirstOpponent) {
        DataBase.goLeftFirstOpponent = goLeftFirstOpponent;
    }

    public static KeyCode getGoRightSecondOpponent() {
        return goRightSecondOpponent;
    }

    public static void setGoRightSecondOpponent(KeyCode goRightSecondOpponent) {
        DataBase.goRightSecondOpponent = goRightSecondOpponent;
    }

    public static KeyCode getGoLeftSecondOpponent() {
        return goLeftSecondOpponent;
    }

    public static void setGoLeftSecondOpponent(KeyCode goLeftSecondOpponent) {
        DataBase.goLeftSecondOpponent = goLeftSecondOpponent;
    }

    public static KeyCode getShootBallFirstOpponent() {
        return shootBallFirstOpponent;
    }

    public static void setShootBallFirstOpponent(KeyCode shootBallFirstOpponent) {
        DataBase.shootBallFirstOpponent = shootBallFirstOpponent;
    }

    public static KeyCode getShootBallSecondOpponent() {
        return shootBallSecondOpponent;
    }

    public static void setShootBallSecondOpponent(KeyCode shootBallSecondOpponent) {
        DataBase.shootBallSecondOpponent = shootBallSecondOpponent;
    }

    public static KeyCode getIceKey() {
        return iceKey;
    }

    public static void setIceKey(KeyCode iceKey) {
        DataBase.iceKey = iceKey;
    }

    public static void setRivalUsername(String rivalUsername) {
        DataBase.rivalUsername = rivalUsername;
    }
}
