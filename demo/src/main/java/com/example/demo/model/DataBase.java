package com.example.demo.model;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataBase {
    private static ArrayList<User> users = new ArrayList<>();

    private static int ballNumber = 10;
    private static double windRange = 1.5;
    private static double rotateSpeed = 0.07;
    private static int iceProgressTime = 5;
    private static int numberOfDefaultBalls = 3;
    private static boolean isTwosomeGame = true;
    private static double stopDistance = 180;
    private static boolean isBlackAndWhite = true;

    private static GameBoard currentGame = new GameBoard(ballNumber, windRange,rotateSpeed, iceProgressTime, 1, isTwosomeGame, stopDistance, isBlackAndWhite);

    private static User currentUser;

    public static void readDataFromBase() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("C:\\Users\\Mahdi\\Desktop\\Class\\Term 2\\AP\\2\\demo\\src\\main\\resources\\com\\example\\demo\\UsersDataBase\\users.json");
        List<User> loadedUser = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        reader.close();
        if (loadedUser != null && !loadedUser.isEmpty())
            users.addAll(loadedUser);
    }

    public static void writeDataToBase() throws IOException {
        System.out.println(users.size());
        Gson gson = new Gson();
        String json = gson.toJson(users);
        FileWriter writer = new FileWriter("C:\\Users\\Mahdi\\Desktop\\Class\\Term 2\\AP\\2\\demo\\src\\main\\resources\\com\\example\\demo\\UsersDataBase\\users.json");
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
        DataBase.currentGame = new GameBoard(ballNumber, windRange, rotateSpeed, iceProgressTime, numberOfDefaultBalls, isTwosomeGame, stopDistance, isBlackAndWhite);
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

    public static void setIsBlackAndWhite(boolean isBlackAndWhite) {
        DataBase.isBlackAndWhite = isBlackAndWhite;
    }
}
