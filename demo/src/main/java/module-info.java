module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;

    exports com.example.demo.model;
    opens com.example.demo.model to com.google.gson;


//    opens com.example.demo.view to javafx.fxml;
//    exports com.example.demo.view;
    exports com.example.demo.view;
    opens com.example.demo.view to javafx.fxml;
}