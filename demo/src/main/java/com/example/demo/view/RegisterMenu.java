package com.example.demo.view;

import com.example.demo.model.DataBase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import javafx.scene.control.Button;


import static com.example.demo.view.SomeFields.*;


public class RegisterMenu extends Application {

    private ImageView imageView;



    @Override
    public void start(Stage primaryStage) throws Exception {
        stopGameSong();
        playSong();

        setScene(primaryStage);
        primaryStage.show();
    }

    private void setScene(Stage stage) throws FileNotFoundException {
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(scrollPane);
        scrollPane.setContent(vBox);
        stage.setTitle(Labels.getLabel(Labels.REGISTER_MENU));

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/demo/css/Style.css")).toExternalForm());
        setVBOX(vBox, stage);

        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }

    private void setVBOX(VBox vBox, Stage stage) throws FileNotFoundException {
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinHeight(HEIGHT);
        vBox.setMinWidth(WIDTH);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);
        setBackground(vBox);
        addFormName(vBox, Labels.getLabel(Labels.REGISTER_MENU));
        addName(vBox);
        addPassword(vBox);
        addImage(vBox, stage);
        addButtons(vBox, stage);
    }


    private void addButtons(VBox vBox, Stage stage) {
        Button signUpButton = new Button(Labels.getLabel(Labels.SIGN_UP));
        handleButtonColor(signUpButton, 1);

        Button loginButton = new Button(Labels.getLabel(Labels.GO_TO_LOGIN_MENU));
        handleButtonColor(loginButton, 2);

        Button skipButton = new Button(Labels.getLabel(Labels.SKIP));
        handleButtonColor(skipButton, 3);

        Button exitButton = new Button(Labels.getLabel(Labels.EXIT));
        handleButtonColor(exitButton, 4);


// Button hover effects
        signUpButton.setOnMouseClicked(mouseEvent -> {
            try {
                RegisterMenuController.getInstance().handleSubmitButtonAction(((TextField) vBox.getChildren().get(2)),
                        ((PasswordField) vBox.getChildren().get(4)), imageView, stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        loginButton.setOnMouseClicked(mouseEvent -> {
            try {
                RegisterMenuController.getInstance().goToLoginMenu(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        skipButton.setOnMouseClicked(event -> {
            try {
                RegisterMenuController.getInstance().skip(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        exitButton.setOnMouseClicked(mouseEvent -> RegisterMenuController.getInstance().exit(stage));


        vBox.getChildren().add(signUpButton);
        vBox.getChildren().add(loginButton);
        vBox.getChildren().add(skipButton);
        vBox.getChildren().add(exitButton);
    }


    private void addImage(VBox vBox, Stage stage) {

        Label avatarLabel = new Label(Labels.getLabel(Labels.AVATAR));
        avatarLabel.getStyleClass().add("label");

        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/5.jpg")).toExternalForm());

        ImageView imageView1 = createImageView(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/1.jpg")).toExternalForm());
        ImageView imageView2 = createImageView(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/2.jpg")).toExternalForm());
        ImageView imageView3 = createImageView(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/3.jpg")).toExternalForm());
        ImageView imageView4 = createImageView(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/4.jpg")).toExternalForm());
        ImageView imageView5 = createImageView(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/5.jpg")).toExternalForm());
        ImageView imageView6 = createImageView(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/6.jpg")).toExternalForm());
        ImageView imageView7 = createImageView(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/Converted/7.jpg")).toExternalForm());


        stage.getIcons().add(icon);
        // Create a GridPane and add the ImageViews to it
        Button uploadButton = new Button(Labels.getLabel(Labels.UPLOAD_AVATAR));
        handleButtonColor(uploadButton, 5);
        uploadButton.setOnMouseClicked(mouseEvent -> RegisterMenuController.getInstance().uploadImage(this, stage));

        Button randomButton = new Button(Labels.getLabel(Labels.RANDOM_AVATAR));
        handleButtonColor(randomButton, 6);
        randomButton.setOnMouseClicked(mouseEvent -> RegisterMenuController.getInstance().setRandomImage(this));


        GridPane gridPane = new GridPane();
        gridPane.add(randomButton, 0, 0);
        gridPane.add(uploadButton, 1, 0);
        gridPane.add(imageView3, 0, 1);
        gridPane.add(imageView4, 1, 1);
        gridPane.add(imageView5, 0, 2);
        gridPane.add(imageView6, 1, 2);
        gridPane.add(imageView7, 0, 3);
        gridPane.add(imageView1, 1, 3);
        gridPane.add(imageView2, 0, 4);


        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(320);
        scrollPane.setMaxHeight(200);
        scrollPane.setMinHeight(160);
        scrollPane.setContent(gridPane);

        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                node.setOnMouseClicked(event -> imageView = ((ImageView) node));
            }
        }

        vBox.getChildren().add(avatarLabel);
        vBox.getChildren().add(scrollPane);
    }

    public static void main(String[] args) throws IOException {
        DataBase.readDataFromBase();
        DataBase.readSettings();
        launch(args);
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

