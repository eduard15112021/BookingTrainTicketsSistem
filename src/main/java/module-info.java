module com.example.myproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.myproject to javafx.fxml;
    exports com.example.myproject;

}