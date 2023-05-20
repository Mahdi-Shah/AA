package com.example.demo.view;

import com.example.demo.controller.LoginController;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginMenuController {

    private static LoginMenuController menuController = null;

    public void back(Stage stage) throws Exception {
        (new RegisterMenu()).start(stage);
    }

    public void login(TextField textField, PasswordField passwordField, Stage stage) throws Exception {
        if (textField.getText().isEmpty()) {
            (new Alert(Alert.AlertType.ERROR, "your username is empty")).show();
            return;
        }
        if (passwordField.getText().isEmpty()) {
            (new Alert(Alert.AlertType.ERROR, "your password is empty")).show();
            return;
        }
        LoginController controller = LoginController.getInstance();
        if (controller.canLogin(textField.getText(), passwordField.getText())) {
            (new MainMenu()).start(stage);
        } else
            (new Alert(Alert.AlertType.ERROR, "your username or password is wrong")).show();
    }

    public static LoginMenuController getInstance() {
        if (menuController == null)
            menuController = new LoginMenuController();
        return menuController;
    }
}
