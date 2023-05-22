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
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.ENTER_CORRECT_NAME))).show();
            return;
        }
        if (ProfileController.getInstance().canChangeName(textField.getText())) {
            (new Alert(Alert.AlertType.INFORMATION, Labels.getLabel(Labels.CHANGES_SAVED_SUCCESSFULLY))).show();
        } else {
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.YOUR_USERNAME_IS_REPETITIVE))).show();
        }
    }

    public void changePassword(PasswordField passwordField) {
        if (passwordField.getText().isEmpty()) {
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.PASSWORD_FIELD_CANT_BE_EMPTY))).show();
            return;
        }
        if (ProfileController.getInstance().canChangePassword(passwordField.getText())) {
            (new Alert(Alert.AlertType.INFORMATION, Labels.getLabel(Labels.CHANGES_SAVED_SUCCESSFULLY))).show();
        } else
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.YOUR_PASSWORD_IS_INVALID))).show();
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
