package com.example.demo.view;

import com.example.demo.controller.GameController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Objects;

import static com.example.demo.view.SomeFields.*;

public class ScoreMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        setBackground(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        addFormName(vBox, Labels.getLabel(Labels.SCORES));

        Button backButton = new Button(Labels.getLabel(Labels.BACK));
        handleButtonColor(backButton, 1);

        backButton.setOnMouseClicked(event -> {
            try {
                PauseMenuController.getInstance().back(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        Label timeLabel = new Label(Labels.getLabel(Labels.TIME) + " : " + LocalTime.of(0, GameController.getInstance().getMinutes(),
                GameController.getInstance().getSeconds()));
        vBox.getChildren().add(timeLabel);


        GameController.getInstance().addWinGamePrize();


        if (GameMenuController.getInstance().isTwoSomeGame()) {
            Label firstPlayerScore = new Label(Labels.getLabel(Labels.FIRST_PLAYER_SCORE) + " = " + GameMenuController.getInstance().getPlayerScore(true));
            Label secondPlayerScore = new Label(Labels.getLabel(Labels.SECOND_PLAYER_SCORE) + " = " + GameMenuController.getInstance().getPlayerScore(false));
            vBox.getChildren().addAll(firstPlayerScore, secondPlayerScore);

        } else {
            Label playerScore = new Label(Labels.getLabel(Labels.PLAYER_SCORE) + " = " + GameController.getInstance().getPlayerScore(true));
            vBox.getChildren().add(playerScore);
        }


        vBox.getChildren().add(backButton);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
