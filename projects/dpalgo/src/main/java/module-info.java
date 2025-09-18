    module com.example.dpalgo {
        requires javafx.controls;
        requires javafx.fxml;


        opens com.example.dpalgo to javafx.fxml;
        exports com.example.dpalgo;
    }