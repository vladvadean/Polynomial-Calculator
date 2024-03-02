module com.example.test1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens GUI to javafx.fxml;
    exports GUI;
    exports DataModels;
    opens DataModels to javafx.fxml;
    exports BusinessLogic;
    opens BusinessLogic to javafx.fxml;
}