package com.example.studentratingnew.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PickSemesterScene extends BaseScene {
    public PickSemesterScene(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public void show() {
        Label name_label = new Label("Введіть скорочення освітньої програми:");
        TextField nameField = new TextField();

        Label semester_label = new Label("Виберіть семестр:");
        ChoiceBox<String> semesterField = new ChoiceBox<>();
        semesterField.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8");

        Button continueButton = new Button("Продовжити");
        continueButton.setOnAction(_ -> {
            String name = nameField.getText();
            String semester = semesterField.getValue();

            if (name.isEmpty() || semester == null) {
                showAlert("Помилка вводу", "Будь ласка, заповніть всі поля.");
            } else {
                InputGradesScene.show(primaryStage, Integer.parseInt(semester), name);
            }
        });

        VBox vbox = new VBox(10, name_label, nameField, semester_label, semesterField, continueButton);
        Scene pick_semester_scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(pick_semester_scene);
        primaryStage.show();
    }
}
