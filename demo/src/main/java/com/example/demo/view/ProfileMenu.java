package com.example.demo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.Objects;

import static com.example.demo.view.SomeFields.*;
import static com.example.demo.view.SomeFields.addFormName;

public class ProfileMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
    }

    private void setStage(Stage stage) throws FileNotFoundException {
        stage.setScene(setScene(stage));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }

    private Scene setScene(Stage stage) throws FileNotFoundException {
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        stage.setTitle(Labels.getLabel(Labels.PROFILE_MENU));
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        setVBOX(vBox, stage);
        return scene;
    }

    private void setVBOX(VBox vBox, Stage stage) throws FileNotFoundException {
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinHeight(HEIGHT);
        vBox.setMinWidth(WIDTH);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);
        setBackground(vBox);
        addFormName(vBox, Labels.getLabel(Labels.PROFILE_MENU));
        addButtons(vBox, stage);
    }

    private void addButtons(VBox vBox, Stage stage) {

        Button changeName = new Button(Labels.getLabel(Labels.CHANGE_USERNAME));
        handleButtonColor(changeName, 1);

        Button changePassword = new Button(Labels.getLabel(Labels.CHANGE_PASSWORD));
        handleButtonColor(changePassword, 2);

        Button logoutButton = new Button(Labels.getLabel(Labels.LOGOUT));
        handleButtonColor(logoutButton, 3);

        Button deleteAccountButton = new Button(Labels.getLabel(Labels.DELETE_ACCOUNT));
        handleButtonColor(deleteAccountButton, 4);

        Button backButton = new Button(Labels.getLabel(Labels.BACK));
        handleButtonColor(backButton, 5);

        changeName.setOnMouseClicked(event -> ProfileMenuController.getInstance().changeName(((TextField) vBox.getChildren().get(2))));

        changePassword.setOnMouseClicked(event -> ProfileMenuController.getInstance().changePassword(((PasswordField) vBox.getChildren().get(5))));

        logoutButton.setOnMouseClicked(event -> {
            try {
                ProfileMenuController.getInstance().logout(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        deleteAccountButton.setOnMouseClicked(event -> {
            try {
                ProfileMenuController.getInstance().deleteAccount(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        backButton.setOnMouseClicked(event -> {
            try {
                ProfileMenuController.getInstance().back(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        addName(vBox);
        vBox.getChildren().add(changeName);
        addPassword(vBox);
        vBox.getChildren().add(changePassword);
        vBox.getChildren().add(logoutButton);
        vBox.getChildren().add(deleteAccountButton);
        vBox.getChildren().add(backButton);
    }
}
