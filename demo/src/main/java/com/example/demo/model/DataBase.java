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

    private static GameBoard currentGame = new GameBoard(12, 1.2, 200, 15, 15, 0.07, 120, 5);

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

    public static void setCurrentGame(GameBoard currentGame) {
        DataBase.currentGame = currentGame;
    }

    public static void addUser(User user) throws IOException {
        users.add(user);
        writeDataToBase();
    }
}
