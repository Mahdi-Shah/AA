package com.example.demo.view;

import com.example.demo.controller.ProfileController;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ProfileMenuController {
    private static ProfileMenuController menuController = null;


    public static ProfileMenuController getInstance() {
        if (menuController == null)
            menuController = new ProfileMenuController();
        return menuController;
    }

    public void changeName(TextField textField) {
        if (textField.getText().isEmpty()) {
            (new Alert(Alert.AlertType.ERROR, "Enter correct name")).show();
            return;
        }
        if (ProfileController.getInstance().canChangeName(textField.getText())) {
            (new Alert(Alert.AlertType.INFORMATION, "Your name changed successfully")).show();
        } else {
            (new Alert(Alert.AlertType.ERROR, "Your name is repetitive")).show();
        }
    }

    public void changePassword(PasswordField passwordField) {
        if (passwordField.getText().isEmpty()) {
            (new Alert(Alert.AlertType.ERROR, "password field can't be empty")).show();
            return;
        }
        if (ProfileController.getInstance().canChangePassword(passwordField.getText())) {
            (new Alert(Alert.AlertType.CONFIRMATION, "Your password changed successfully")).show();
        } else
            (new Alert(Alert.AlertType.ERROR, "Your password is invalid")).show();
    }

    public void logout(Stage stage) throws Exception {
        ProfileController.getInstance().logout();
        (new RegisterMenu()).start(stage);
    }

    public void deleteAccount(Stage stage) throws Exception {
        ProfileController.getInstance().deleteAccount();
        (new RegisterMenu()).start(stage);
    }

    public void back(Stage stage) throws Exception {
        (new MainMenu()).start(stage);
    }
}
