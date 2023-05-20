package com.example.demo.controller;

import com.example.demo.model.DataBase;
import com.example.demo.model.User;

import java.io.IOException;

public class ProfileController {

    private static ProfileController controller = null;


    public static ProfileController getInstance() {
        if (controller == null)
            controller = new ProfileController();
        return controller;
    }

    public boolean canChangeName(String username) {
        for (User user : DataBase.getUsers())
            if (user.getUsername().equals(username))
                return false;
        DataBase.getCurrentUser().setUsername(username);
        return true;
    }

    public boolean canChangePassword(String text) {
        DataBase.getCurrentUser().setPassword(text);
        return true;
    }

    public void deleteAccount() throws IOException {
        DataBase.getUsers().remove(DataBase.getCurrentUser());
        DataBase.writeDataToBase();
    }
}
