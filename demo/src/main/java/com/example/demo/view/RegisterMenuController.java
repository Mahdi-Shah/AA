package com.example.demo.view;

import com.example.demo.controller.RegisterController;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Random;
import static com.example.demo.view.SomeFields.createImageView;

public class RegisterMenuController {

    private static RegisterMenuController menuController = new RegisterMenuController();

    public void handleSubmitButtonAction(TextField nameField, PasswordField passwordField, ImageView imageView, Stage stage) throws Exception {
        if(nameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.PLEASE_ENTER_YOUR_NAME));
            alert.show();
            return;
        }
        if(passwordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.PLEASE_ENTER_YOUR_PASSWORD));
            alert.show();
            return;
        }
        if (imageView == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.please_choose_your_avatar));
            alert.show();
            return;
        }
        RegisterController controller = RegisterController.getInstance();
        if (controller.canRegister(nameField.getText(), passwordField.getText(), imageView)) {
            (new MainMenu()).start(stage);
        } else
            (new Alert(Alert.AlertType.ERROR, Labels.getLabel(Labels.YOUR_USERNAME_IS_REPETITIVE))).show();
    }

    public void goToLoginMenu(Stage stage) throws Exception {
        LoginMenu menu = new LoginMenu();
        menu.start(stage);
    }

    public void exit(Stage stage) {
        stage.close();
    }

    public void uploadImage(RegisterMenu registerMenu, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            // Load the selected image into the ImageView
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            registerMenu.setImageView(imageView);
        }
    }

    public void setRandomImage(RegisterMenu registerMenu) {
        Random random = new Random();
        int number = random.nextInt(7) + 1;
        ImageView imageView = createImageView("C:\\Users\\Mahdi\\Desktop\\Class\\Term 2\\AP\\2\\demo\\src\\main\\resources\\com\\example\\demo\\images\\Converted\\" + number + ".jpg");
        registerMenu.setImageView(imageView);
    }

    public void skip(Stage stage) throws Exception {
        RegisterController.getInstance().skip();
        (new GameMenu()).start(stage);
    }

    public static RegisterMenuController getInstance() {
        if (menuController == null)
            menuController = new RegisterMenuController();
        return menuController;
    }
}