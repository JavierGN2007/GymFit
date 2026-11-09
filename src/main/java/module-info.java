module com.example.gymfit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gymfit to javafx.fxml;
    exports com.example.gymfit;
}