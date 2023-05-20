package com.example.demo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

import static com.example.demo.view.SomeFields.*;


public class LoginMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
    }

    private Scene setScene(Stage stage) throws FileNotFoundException {
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        stage.setTitle("Login Menu");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        setVBOX(vBox, stage);
        return scene;
    }

    private void setStage(Stage stage) throws FileNotFoundException {

        stage.setScene(setScene(stage));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }

    private void setVBOX(VBox vBox, Stage stage) throws FileNotFoundException {
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinHeight(HEIGHT);
        vBox.setMinWidth(WIDTH);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);
        setBackground(vBox);
        addFormName(vBox, "Wellcome to Login menu");
        addName(vBox);
        addPassword(vBox);
        addButtons(vBox, stage);
    }

    private void addButtons(VBox vBox, Stage stage) {
        Button loginButton = new Button("Login");
        handleButtonColor(loginButton, 1);

        Button backButton = new Button("Back");
        handleButtonColor(backButton, 2);

        loginButton.setOnMouseClicked(event -> {
            try {
                LoginMenuController.getInstance().login(((TextField) vBox.getChildren().get(2)), ((PasswordField) vBox.getChildren().get(4)), stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        backButton.setOnMouseClicked(mouseEvent -> {
            try {
                LoginMenuController.getInstance().back(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        vBox.getChildren().add(loginButton);
        vBox.getChildren().add(backButton);
    }


    private void setBackground(VBox vBox) throws FileNotFoundException {

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
}
