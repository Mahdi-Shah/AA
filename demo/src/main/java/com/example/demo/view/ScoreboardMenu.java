package com.example.demo.view;


import com.example.demo.model.DataBase;
import com.example.demo.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Objects;

import static com.example.demo.view.SomeFields.handleButtonColor;
import static com.example.demo.view.SomeFields.setRegisterAndLoginBackground;

public class ScoreboardMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        TableView<User> table = new TableView<>();


        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        TableColumn<User, Integer> scoreCol = new TableColumn<>("Score");
        TableColumn<User, Integer> easyScoreCol = new TableColumn<>("easy");
        TableColumn<User, Integer> middleScoreCol = new TableColumn<>("middle");
        TableColumn<User, Integer> hardScoreCol = new TableColumn<>("hard");
        TableColumn<User, Integer> timeCol = new TableColumn<>("Time");
        TableColumn<User, Integer> easyTimeCol = new TableColumn<>("easy");
        TableColumn<User, Integer> middleTimeCol = new TableColumn<>("middle");
        TableColumn<User, Integer> hardTimeCol = new TableColumn<>("hard");


        table.getColumns().addAll(usernameCol, scoreCol, easyScoreCol, middleScoreCol, hardScoreCol,
                timeCol, easyTimeCol, middleTimeCol, hardTimeCol);


        ObservableList<User> data = FXCollections.observableArrayList(DataBase.getUsers());
        table.setItems(data);


        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setMinWidth(120);
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setMinWidth(70);
        easyScoreCol.setCellValueFactory(new PropertyValueFactory<>("easyScore"));
        middleScoreCol.setCellValueFactory(new PropertyValueFactory<>("middleScore"));
        hardScoreCol.setCellValueFactory(new PropertyValueFactory<>("hardScore"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("averageTime"));
        easyTimeCol.setCellValueFactory(new PropertyValueFactory<>("easyAverageTime"));
        easyTimeCol.setCellValueFactory(new PropertyValueFactory<>("middleAverageTime"));
        easyTimeCol.setCellValueFactory(new PropertyValueFactory<>("hardAverageTime"));
        timeCol.setMinWidth(70);
        easyScoreCol.setMinWidth(80);
        middleScoreCol.setMinWidth(80);
        hardScoreCol.setMinWidth(80);
        easyTimeCol.setMinWidth(80);
        middleTimeCol.setMinWidth(80);
        hardTimeCol.setMinWidth(80);


        table.setRowFactory(tv -> {
            return new TableRow<User>() {
                @Override
                protected void updateItem(User user, boolean empty) {
                    super.updateItem(user, empty);

                    if (user == null || empty) {
                        setStyle("");
                    } else if (getIndex() == 0) {
                        setStyle("-fx-background-color: gold;");
                    } else if (getIndex() == 1) {
                        setStyle("-fx-background-color: silver;");
                    } else if (getIndex() == 2) {
                        setStyle("-fx-background-color: #cd7f32;"); // Bronze color
                    } else {
                        setStyle("");
                    }
                }
            };
        });

        Button backButton = new Button("Back");
        handleButtonColor(backButton, 1);

        backButton.setOnMouseClicked(event -> {
            try {
                ScoreBoardMenuController.getInstance().back(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        table.getSortOrder().add(scoreCol);
        table.getSortOrder().add(timeCol);
        table.sort();


        VBox root = new VBox();
        root.setSpacing(30);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(table, backButton);
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/style.css")).toExternalForm());
        setRegisterAndLoginBackground(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}