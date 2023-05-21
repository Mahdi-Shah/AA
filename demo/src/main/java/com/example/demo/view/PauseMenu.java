package com.example.demo.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.view.SomeFields.handleButtonColor;

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

        handleButtonColor(restartButton, 1);
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
                (new MainMenu()).start(primaryStage);
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
        vBox.getChildren().addAll(resumeButton, restartButton, saveGameButton, exitButton);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}