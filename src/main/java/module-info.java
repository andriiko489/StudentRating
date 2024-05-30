module com.example.studentratingnew {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.studentratingnew to javafx.fxml;
    exports com.example.studentratingnew;
    exports com.example.studentratingnew.scenes;
    opens com.example.studentratingnew.scenes to javafx.fxml;
}