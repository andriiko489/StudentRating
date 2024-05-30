package com.example.studentratingnew.scenes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public abstract class BaseScene {
    protected Stage primaryStage;

    public BaseScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    protected void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public abstract void show();
}
