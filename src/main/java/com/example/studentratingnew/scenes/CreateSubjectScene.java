package com.example.studentratingnew.scenes;

import com.example.studentratingnew.DatabaseManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CreateSubjectScene extends BaseScene {
    private int semester;
    private String specializationName;

    public CreateSubjectScene(Stage primaryStage, int semester, String specializationName) {
        super(primaryStage);
        this.semester = semester;
        this.specializationName = specializationName;
    }

    @Override
    public void show() {
        Label name_label = new Label("Введіть назву предмета:");
        TextField nameField = new TextField();

        Label credits_label = new Label("Введіть кількість кредитів:");
        TextField creditsField = new TextField();

        Button addButton = new Button("Додати предмет");

        addButton.setOnAction(_ -> {
            String name = nameField.getText();
            String creditsText = creditsField.getText();
            if (name.isEmpty() || creditsText.isEmpty()) {
                showAlert("Помилка вводу", "Будь ласка, заповніть всі поля.");
                return;
            }

            int credits;
            try {
                credits = Integer.parseInt(creditsText);
            } catch (NumberFormatException e) {
                showAlert("Помилка вводу", "Кількість кредитів повинна бути числом.");
                return;
            }

            DatabaseManager dbManager;
            try {
                dbManager = new DatabaseManager("jdbc:postgresql://localhost:5432/mydatabase", "myuser", "mypassword");
                dbManager.createSubject(specializationName, name, semester, credits);
            } catch (SQLException ex) {
                showAlert("Помилка бази даних", "Не вдалося з'єднатися з базою даних або додати предмет.");
                ex.printStackTrace();
                return;
            }

            InputGradesScene.show(primaryStage, semester, specializationName);
        });

        VBox vbox = new VBox(name_label, nameField, credits_label, creditsField, addButton);
        Scene create_subject_scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(create_subject_scene);
        primaryStage.show();
    }
}
