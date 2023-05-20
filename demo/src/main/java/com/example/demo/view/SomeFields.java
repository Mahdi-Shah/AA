package com.example.demo.view;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface SomeFields {

    int HEIGHT = 700;
    int WIDTH = 600;

    static void addName(VBox vBox) {
        Label nameLabel = new Label("Name:");
        nameLabel.getStyleClass().add("label");


        TextField nameField = new TextField();
        nameField.getStyleClass().add("text-field");
        nameField.setPromptText("Enter your name");
        nameField.setMaxWidth(250);

        vBox.getChildren().add(nameLabel);
        vBox.getChildren().add(nameField);
    }

    static void addFormName(VBox vBox, String string) {
        Label label = new Label(string);
        label.getStyleClass().add("my-label");
        vBox.getChildren().add(label);
    }

    static void addPassword(VBox vBox) {
        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("label");


        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("password-field");
        passwordField.setPromptText("Enter your password");
        passwordField.setFont(Font.font("Lato", FontWeight.NORMAL, 14));
        passwordField.setMaxWidth(250);

        vBox.getChildren().add(passwordLabel);
        vBox.getChildren().add(passwordField);
    }

    static void setButtonSetting(Button button) {
        button.setFont(Font.font("Lato", FontWeight.BOLD, 14));
        button.setTextFill(Color.WHITE);
        button.setPadding(new Insets(10, 30, 10, 30));
        button.setEffect(new DropShadow());
        button.setCursor(Cursor.HAND);
        button.setMaxWidth(180);
    }

    static void setRegisterAndLoginBackground(VBox vBox) throws FileNotFoundException {

        FileInputStream inputStream = new FileInputStream("C:\\Users\\Mahdi\\Desktop\\Class\\Term 2\\AP\\2\\demo\\src\\main\\resources\\com\\example\\demo\\images\\background.jpg");
        Image image = new Image(inputStream);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        vBox.setBackground(background);
    }

    static void handleButtonColor(Button button, int code) {
        setButtonSetting(button);
        switch (code) {
            case 1:
                button.setBackground(new Background(new BackgroundFill(Color.web("#3498db"), new CornerRadii(5), null)));
                button.setOnMouseEntered(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#2980b9"), new CornerRadii(5), null)));
                });
                button.setOnMouseExited(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#3498db"), new CornerRadii(5), null)));
                });
                break;
            case 2:
                button.setBackground(new Background(new BackgroundFill(Color.web("#2ecc71"), new CornerRadii(5), null)));
                button.setOnMouseEntered(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#27ae60"), new CornerRadii(5), null)));
                });
                button.setOnMouseExited(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#2ecc71"), new CornerRadii(5), null)));
                });
                break;
            case 3:
                button.setBackground(new Background(new BackgroundFill(Color.web("#e74c3c"), new CornerRadii(5), null)));
                button.setOnMouseEntered(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#c0392b"), new CornerRadii(5), null)));
                });
                button.setOnMouseExited(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#e74c3c"), new CornerRadii(5), null)));
                });
                break;
            case 4:
                button.setBackground(new Background(new BackgroundFill(Color.web("#34495e"), new CornerRadii(5), null)));
                button.setOnMouseEntered(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#2c3e50"), new CornerRadii(5), null)));
                });
                button.setOnMouseExited(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#34495e"), new CornerRadii(5), null)));
                });
                break;
            case 5:
                button.setBackground(new Background(new BackgroundFill(Color.web("#fde93b"), new CornerRadii(5), null)));
                button.setOnMouseEntered(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#cfbe31"), new CornerRadii(5), null)));
                });
                button.setOnMouseExited(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#fde93b"), new CornerRadii(5), null)));
                });
                break;
            case 6:
                button.setBackground(new Background(new BackgroundFill(Color.web("#7c20a1"), new CornerRadii(5), null)));
                button.setOnMouseEntered(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#561771"), new CornerRadii(5), null)));
                });
                button.setOnMouseExited(event -> {
                    button.setBackground(new Background(new BackgroundFill(Color.web("#7c20a1"), new CornerRadii(5), null)));
                });
                break;
        }
    }

    static ImageView createImageView(String path) {
        ImageView imageView = new ImageView(new Image(path));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        return imageView;
    }
}
