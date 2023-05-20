package com.example.demo.view;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.Objects;

import static com.example.demo.view.SomeFields.*;

public class ScoreboardMenu extends Application {

    private int difficulty = 0;

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
        stage.setTitle("Main Menu");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        setVBOX(vBox, stage);
        return scene;
    }

    private void setVBOX(VBox vBox, Stage stage) throws FileNotFoundException {
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinHeight(HEIGHT);
        vBox.setMinWidth(WIDTH);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);
        setRegisterAndLoginBackground(vBox);
        addFormName(vBox, "Wellcome to Scoreboard menu");
        addScoreboard(vBox);
        addButtons(vBox, stage);
    }

    private void addButtons(VBox vBox, Stage stage) {
        Button easyDifficultyButton = new Button("Sort by\neasy games");
        handleButtonColor(easyDifficultyButton, 1);

        Button middleDifficultyButton = new Button("Sort by\nmiddle games");
        handleButtonColor(middleDifficultyButton, 2);

        Button hardDifficultyButton = new Button("Sort by\nhard games");
        handleButtonColor(hardDifficultyButton, 3);

        Button backButton = new Button("Back");
        handleButtonColor(backButton, 4);

        easyDifficultyButton.setOnMouseClicked(event -> {
            this.difficulty = 1;
            ScoreBoardMenuController.getInstance().setScoreBoardByDifficulty(this.difficulty,
                    ((GridPane) ((ScrollPane) vBox.getChildren().get(1)).getContent()));
        });

        middleDifficultyButton.setOnMouseClicked(event -> {
            this.difficulty = 2;
            ScoreBoardMenuController.getInstance().setScoreBoardByDifficulty(this.difficulty,
                    ((GridPane) ((ScrollPane) vBox.getChildren().get(1)).getContent()));
        });

        hardDifficultyButton.setOnMouseClicked(event -> {
            this.difficulty = 3;
            ScoreBoardMenuController.getInstance().setScoreBoardByDifficulty(this.difficulty,
                    ((GridPane) ((ScrollPane) vBox.getChildren().get(1)).getContent()));
        });

        backButton.setOnMouseClicked(event -> {
            try {
                ScoreBoardMenuController.getInstance().back(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(15);
        hBox.getChildren().add(easyDifficultyButton);
        hBox.getChildren().add(middleDifficultyButton);
        hBox.getChildren().add(hardDifficultyButton);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(backButton);
    }

    private void addScoreboard(VBox vBox) {
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        ScoreBoardMenuController.getInstance().setScoreBoardByDifficulty(this.difficulty, gridPane);
        scrollPane.setContent(gridPane);
        vBox.getChildren().add(scrollPane);
    }
}
