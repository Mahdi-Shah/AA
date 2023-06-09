package com.example.demo.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.demo.view.Labels.getLabel;
import static com.example.demo.view.SomeFields.*;

public class StartMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        CheckBox twosomeGame = new CheckBox(getLabel(Labels.TWOSOME_GAME));
        twosomeGame.setSelected(false);
        twosomeGame.setAlignment(Pos.CENTER);
        twosomeGame.getStyleClass().add("checkbox");

        TextField nameField = new TextField();
        nameField.getStyleClass().add("text-field");
        nameField.setPromptText(getLabel(Labels.ENTER_RIVAL_NAME));
        nameField.setMaxWidth(250);


        ChoiceBox<Integer> mapNumber = new ChoiceBox<>();
        mapNumber.getItems().addAll(1, 2, 3);
        mapNumber.setValue(2);

        Button playGameButton = new Button(getLabel(Labels.PLAY_GAME));
        handleButtonColor(playGameButton, 1);

        playGameButton.setOnMouseClicked(event -> {
            try {
                StartMenuController.getInstance().playGame(twosomeGame, nameField, mapNumber, primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        VBox vBox = new VBox(20, twosomeGame, nameField, mapNumber, playGameButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.getStyleClass().add("vbox");

        Scene scene = new Scene(vBox, WIDTH, HEIGHT);
        setBackground(vBox);


        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());


        primaryStage.setTitle(getLabel(Labels.START_GAME));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
