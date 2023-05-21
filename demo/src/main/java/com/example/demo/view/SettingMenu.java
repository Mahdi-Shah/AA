package com.example.demo.view;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Objects;

import static com.example.demo.view.SomeFields.*;

public class SettingMenu extends Application {
    private int ballCount = 5;
    private int rotationSpeed = 10;
    private double windSpeed = 1.5;
    private int freezeTime = 5;
    private boolean isMuted = false;
    private boolean isBlackAndWhite = false;
    private String language = "English";

    private String keyName = "Stop";

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Slider ballCountSlider = new Slider(0, 80, ballCount);
        Slider rotationSpeedSlider = new Slider(0, 20, rotationSpeed);
        Slider windSpeedSlider = new Slider(0, 3, windSpeed);
        Slider freezeTimeSlider = new Slider(0, 10, freezeTime);
        CheckBox muteCheckBox = new CheckBox("Mute");
        CheckBox blackAndWhiteCheckBox = new CheckBox("Black & White");
        ChoiceBox<String> languageChoiceBox = new ChoiceBox<>();
        ChoiceBox<String> keyChoiceBox = new ChoiceBox<>();
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back to Main Menu");



        languageChoiceBox.getItems().addAll("English", "فارسی");
        languageChoiceBox.getStyleClass().add("choice-box");
        languageChoiceBox.setValue(language);


        keyChoiceBox.getItems().addAll("Stop", "Right (1st player)", "Left (1st player)",
                "Right (2th player)", "Left (2th player)", "Shoot (1st player)", "Shoot (2th player)", "Freeze");
        keyChoiceBox.getStyleClass().add("choice-box");
        keyChoiceBox.setValue(keyName);




        ballCountSlider.setShowTickLabels(true);
        ballCountSlider.setShowTickMarks(true);
        ballCountSlider.setMajorTickUnit(10);
        ballCountSlider.setSnapToTicks(true);
        ballCountSlider.setMinWidth(250);
        ballCountSlider.getStyleClass().add("slider");
        ballCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> ballCount = newValue.intValue());



        rotationSpeedSlider.setShowTickLabels(true);
        rotationSpeedSlider.setShowTickMarks(true);
        rotationSpeedSlider.setMajorTickUnit(5);
        rotationSpeedSlider.setSnapToTicks(true);
        rotationSpeedSlider.getStyleClass().add("slider");
        rotationSpeedSlider.valueProperty().addListener((observable, oldValue, newValue) -> rotationSpeed = newValue.intValue());


        windSpeedSlider.setShowTickLabels(true);
        windSpeedSlider.setShowTickMarks(true);
        windSpeedSlider.setMajorTickUnit(0.5);
        windSpeedSlider.setSnapToTicks(true);
        windSpeedSlider.getStyleClass().add("slider");
        windSpeedSlider.valueProperty().addListener((observable, oldValue, newValue) -> windSpeed = newValue.doubleValue());


        freezeTimeSlider.setShowTickLabels(true);
        freezeTimeSlider.setShowTickMarks(true);
        freezeTimeSlider.setMajorTickUnit(1);
        freezeTimeSlider.setSnapToTicks(true);
        freezeTimeSlider.getStyleClass().add("slider");
        freezeTimeSlider.valueProperty().addListener((observable, oldValue, newValue) -> freezeTime = newValue.intValue());


        muteCheckBox.setSelected(isMuted);
        muteCheckBox.setAlignment(Pos.CENTER);
        muteCheckBox.getStyleClass().add("checkbox");
        muteCheckBox.setOnAction(event -> isMuted = muteCheckBox.isSelected());


        blackAndWhiteCheckBox.setSelected(isBlackAndWhite);
        blackAndWhiteCheckBox.getStyleClass().add("checkbox");
        blackAndWhiteCheckBox.setOnAction(event -> isBlackAndWhite = blackAndWhiteCheckBox.isSelected());

        languageChoiceBox.setOnAction(event -> {
            language = languageChoiceBox.getValue();
        });

        keyChoiceBox.setOnAction(event -> {
            keyName = keyChoiceBox.getValue();
        });

        keyChoiceBox.setOnKeyPressed(event -> {
            SettingMenuController.getInstance().changeThisKey(keyName, event.getCode());
        });

        handleButtonColor(backButton, 1);
        backButton.setMinWidth(200);
        backButton.setOnMouseClicked(event -> {
            try {
                SettingMenuController.getInstance().back(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        handleButtonColor(submitButton, 2);
        submitButton.setMinWidth(200);
        submitButton.setOnMouseClicked(event -> SettingMenuController.getInstance().submit(ballCount,
                rotationSpeed, windSpeed, freezeTime, isMuted, isBlackAndWhite, language));


        Label ballCountLabel = new Label("Ball Count:");
        Label rotationSpeedLabel = new Label("Rotation Speed:");
        Label windSpeedLabel = new Label("Wind Speed:");
        Label freezeTimeLabel = new Label("Freeze Time:");

        ballCountLabel.getStyleClass().add("slider-label");
        rotationSpeedLabel.getStyleClass().add("slider-label");
        windSpeedLabel.getStyleClass().add("slider-label");
        freezeTimeLabel.getStyleClass().add("slider-label");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, ballCountLabel, ballCountSlider);
        gridPane.addRow(1, rotationSpeedLabel, rotationSpeedSlider);
        gridPane.addRow(2, windSpeedLabel, windSpeedSlider);
        gridPane.addRow(3, freezeTimeLabel, freezeTimeSlider);


        VBox vBox = new VBox(20, gridPane, muteCheckBox, blackAndWhiteCheckBox, languageChoiceBox,
                keyChoiceBox, submitButton, backButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.getStyleClass().add("vbox");

        Scene scene = new Scene(vBox, WIDTH, HEIGHT);


        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());


        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }
}