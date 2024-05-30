package com.example.studentratingnew;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Utils {
    public static void addNumericValidation(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    return;
                }

                try {
                    float value = Float.parseFloat(newValue);
                    if (value < 0 || value > 100) {
                        textField.setText(oldValue);
                    }
                } catch (NumberFormatException e) {
                    textField.setText(oldValue);
                }
            }
        });
    }
}


class SubjectsTableCreate {
    public static void main(String[] args) throws SQLException {
        DatabaseManager dbManager = new DatabaseManager("jdbc:postgresql://localhost:5432/mydatabase", "myuser", "mypassword");
        dbManager.createSubjectsTable();
    }
}