package com.example.demo.controller;

import com.example.demo.model.DataBase;
import com.example.demo.model.User;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class RegisterController {

    private static RegisterController controller = null;

    public boolean canRegister(String username, String password, ImageView avatar) throws IOException {
        if (DataBase.getUserByUsername(username) == null) {
            User newUser = new User(username, password, avatar);
            DataBase.addUser(newUser);
            DataBase.setCurrentUser(newUser);
            return true;
        }
        return false;
    }

    public static RegisterController getInstance() {
        if (controller == null)
            controller = new RegisterController();
        return controller;
    }
}
