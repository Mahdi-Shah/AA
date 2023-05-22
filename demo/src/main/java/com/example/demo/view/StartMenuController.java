package com.example.demo.view;

import com.example.demo.controller.StartController;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartMenuController {
    private static StartMenuController menuController = null;

    public static StartMenuController getInstance() {
        if (menuController == null)
            menuController = new StartMenuController();
        return menuController;
    }

    public void playGame(CheckBox twosomeGame, TextField nameField, ChoiceBox<Integer> mapNumber, Stage stage) throws Exception {
        if (twosomeGame.isSelected())
            if (!StartController.getInstance().isCorrectName(nameField.getText())) {
                (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.YOUR_RIVAL_NAME_IS_INCORRECT))).show();
                return;
            }

        StartController.getInstance().playGame(twosomeGame.isSelected(), nameField.getText(), mapNumber.getValue());
        (new GameMenu()).start(stage);
    }
}
