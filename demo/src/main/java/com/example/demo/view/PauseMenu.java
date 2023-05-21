package com.example.demo.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.demo.view.SomeFields.handleButtonColor;
import static com.example.demo.view.SomeFields.setRegisterAndLoginBackground;

public class PauseMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        Button resumeButton = new Button("Resume");
        Button restartButton = new Button("Restart");
        Button saveGameButton = new Button("Save Game");
        CheckBox muteCheckBox = new CheckBox("Mute");
        ChoiceBox<Integer> musicChoiceBox = new ChoiceBox<>();
        Button exitButton = new Button("Exit");

        musicChoiceBox.getItems().addAll(1, 2, 3);
        musicChoiceBox.setValue(2);

        handleButtonColor(resumeButton, 1);
        handleButtonColor(restartButton, 2);
        handleButtonColor(saveGameButton, 3);
        handleButtonColor(exitButton, 4);

        resumeButton.setOnMouseClicked(event -> {
            try {
                (new GameMenu()).start(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        restartButton.setOnMouseClicked(event -> {
            try {
                PauseMenuController.getInstance().restartGame(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        exitButton.setOnMouseClicked(event -> {
            try {
                PauseMenuController.getInstance().back(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        saveGameButton.setOnMouseClicked(event -> {
            try {
                PauseMenuController.getInstance().saveGame();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(resumeButton, restartButton, saveGameButton,
                muteCheckBox, musicChoiceBox, exitButton);
        vBox.setSpacing(20);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        setRegisterAndLoginBackground(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}