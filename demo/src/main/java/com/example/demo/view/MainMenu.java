package com.example.demo.view;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.Objects;

import static com.example.demo.view.SomeFields.*;


public class MainMenu extends Application {


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
        addFormName(vBox, "Wellcome to Main menu");
        addButtons(vBox, stage);
    }

    private void addButtons(VBox vBox, Stage stage) {
        Button newGameButton = new Button("New Game");
        handleButtonColor(newGameButton, 1);

        Button continueButton = new Button("Continue Game");
        handleButtonColor(continueButton, 2);

        Button profileButton = new Button("Profile Menu");
        handleButtonColor(profileButton, 3);

        Button scoreboardButton = new Button("Scoreboard");
        handleButtonColor(scoreboardButton, 4);

        Button settingButton = new Button("Setting");
        handleButtonColor(settingButton, 5);

        Button exitButton = new Button("Exit");
        handleButtonColor(exitButton, 6);


        newGameButton.setOnMouseClicked(event -> {
            try {
                MainMenuController.getInstance().playNewGame(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        profileButton.setOnMouseClicked(event -> {
            try {
                MainMenuController.getInstance().goToProfile(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        scoreboardButton.setOnMouseClicked(event -> {
            try {
                MainMenuController.getInstance().goToScoreboard(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        settingButton.setOnMouseClicked(event -> {
            try {
                MainMenuController.getInstance().goToSettingMenu(stage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        exitButton.setOnMouseClicked(event -> {
            try {
                MainMenuController.getInstance().exit(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        continueButton.setOnMouseClicked(event -> {
            try {
                MainMenuController.getInstance().continueGame(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        vBox.getChildren().add(newGameButton);
        vBox.getChildren().add(continueButton);
        vBox.getChildren().add(profileButton);
        vBox.getChildren().add(scoreboardButton);
        vBox.getChildren().add(settingButton);
        vBox.getChildren().add(exitButton);
    }
}
