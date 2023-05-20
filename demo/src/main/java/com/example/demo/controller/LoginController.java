package com.example.demo.controller;

import com.example.demo.model.DataBase;
import com.example.demo.model.User;

public class LoginController {

    private static LoginController controller = null;
    public boolean canLogin(String username, String password) {
        if (DataBase.getUsers().isEmpty())
            return false;
        for (User user : DataBase.getUsers())
            if (user.getUsername().equals(username))
                if (user.getPassword().equals(password)) {
                    DataBase.setCurrentUser(user);
                    return true;
                }
        return false;
    }

    public static LoginController getInstance() {
        if (controller == null)
            controller = new LoginController();
        return controller;
    }
}
