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
        setRegisterAndLoginBackground(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        addFormName(vBox, "Scores");

        Button backButton = new Button("Back");
        handleButtonColor(backButton, 1);

        backButton.setOnMouseClicked(event -> {
            try {
                PauseMenuController.getInstance().back(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        Label timeLabel = new Label("time : " + LocalTime.of(0, GameController.getInstance().getMinutes(),
                GameController.getInstance().getSeconds()));
        vBox.getChildren().add(timeLabel);


        GameController.getInstance().addWinGamePrize();


        if (GameMenuController.getInstance().isTwoSomeGame()) {
            Label firstPlayerScore = new Label("First Player Score = " + GameMenuController.getInstance().getPlayerScore(true));
            Label secondPlayerScore = new Label("Second Player Score = " + GameMenuController.getInstance().getPlayerScore(false));
            vBox.getChildren().addAll(firstPlayerScore, secondPlayerScore);

        } else {
            Label playerScore = new Label("Player Score = " + GameController.getInstance().getPlayerScore(true));
            vBox.getChildren().add(playerScore);
        }


        vBox.getChildren().add(backButton);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
