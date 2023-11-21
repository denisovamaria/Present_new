module com.example.present_new {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.present_new to javafx.fxml;
    exports com.example.present_new;
}