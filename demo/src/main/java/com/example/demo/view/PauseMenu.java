package com.example.demo.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.demo.view.SomeFields.handleButtonColor;
import static com.example.demo.view.SomeFields.setBackground;

public class PauseMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        Button resumeButton = new Button(Labels.getLabel(Labels.RESUME));
        Button restartButton = new Button(Labels.getLabel(Labels.RESTART));
        Button saveGameButton = new Button(Labels.getLabel(Labels.SAVE_GAME));
        CheckBox muteCheckBox = new CheckBox(Labels.getLabel(Labels.MUTE));
        ChoiceBox<Integer> musicChoiceBox = new ChoiceBox<>();
        Button exitButton = new Button(Labels.getLabel(Labels.EXIT));

        Label stopKeyLabel = new Label(Labels.getLabel(Labels.STOP) + " = " +
                GameMenuController.getInstance().getStopKey().getName());
        Label rightFirsKeytLabel = new Label(Labels.getLabel(Labels.RIGHT_FIRST) + " = " +
                GameMenuController.getInstance().getRightFirstKey().getName());
        Label leftFirstKeyLabel = new Label(Labels.getLabel(Labels.LEFT_FIRST) + " = " +
                GameMenuController.getInstance().getLeftFirstKey().getName());
        Label rightSecondKeyLabel = new Label(Labels.getLabel(Labels.RIGHT_SECOND) + " = " +
                GameMenuController.getInstance().getRightSecondKey().getName());
        Label leftSecondKeyLabel = new Label(Labels.getLabel(Labels.LEFT_SECOND) + " = " +
                GameMenuController.getInstance().getLeftSecondKey().getName());
        Label shootFirstKeyLabel = new Label(Labels.getLabel(Labels.SHOOT_FIRST) + " = " +
                GameMenuController.getInstance().getShootFirstKey().getName());
        Label shootSecondKeyLabel = new Label(Labels.getLabel(Labels.SHOOT_SECOND) + " = " +
                GameMenuController.getInstance().getShootSecondKey().getName());

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

        muteCheckBox.setOnAction(event -> {
            GameMenuController.getInstance().setGameMute(muteCheckBox.isSelected());
        });

        musicChoiceBox.setOnAction(event -> {
            GameMenuController.getInstance().setGameSongNumber(musicChoiceBox.getValue());
        });

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(resumeButton, restartButton, saveGameButton, stopKeyLabel, rightFirsKeytLabel,
                leftFirstKeyLabel, rightSecondKeyLabel, leftSecondKeyLabel, shootFirstKeyLabel, shootSecondKeyLabel,
                muteCheckBox, musicChoiceBox, exitButton);
        vBox.setSpacing(20);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        setBackground(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}