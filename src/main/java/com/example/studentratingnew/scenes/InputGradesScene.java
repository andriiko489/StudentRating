package com.example.studentratingnew.scenes;

import com.example.studentratingnew.DatabaseManager;
import com.example.studentratingnew.Subject;
import com.example.studentratingnew.Utils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InputGradesScene extends BaseScene {

    private final int semester;
    private final String specializationName;

    public InputGradesScene(Stage primaryStage, int semester, String specializationName) {
        super(primaryStage);
        this.semester = semester;
        this.specializationName = specializationName;
    }

    @Override
    public void show() {
        Label name_label = new Label("Введіть оцінки:");

        VBox vbox = new VBox(name_label);

        DatabaseManager dbManager;
        try {
            dbManager = new DatabaseManager("jdbc:postgresql://localhost:5432/mydatabase", "myuser", "mypassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Subject> subjects;
        try {
            subjects = dbManager.getAllBySubjectAndSemester(semester, specializationName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> subject_names = new ArrayList<>();
        ArrayList<Integer> coefs = new ArrayList<>();
        for (Subject subject : subjects) {
            subject_names.add(subject.getSubjectName());
            coefs.add(subject.getCredits());
        }

        float sum_coef = 0;
        for (float coef : coefs) {
            sum_coef += coef;
        }

        ArrayList<TextField> grades_fields = new ArrayList<>();
        for (String subject : subject_names) {
            TextField grade_field = new TextField();
            Utils.addNumericValidation(grade_field);
            vbox.getChildren().add(new HBox(new Label(subject), grade_field));
            grades_fields.add(grade_field);
        }

        Button continueButton = new Button("Розрахувати");
        Label result = new Label();
        Label error = new Label();
        Button addSubject = new Button("Перейти до сторінки додавання предмету");
        float finalSum_coef = sum_coef;

        continueButton.setOnAction(_ -> {
            double res = 0;
            boolean allFieldsFilled = true;

            for (int i = 0; i < subject_names.size(); i++) {
                String raw_grade = grades_fields.get(i).getText();

                if (raw_grade.isEmpty()) {
                    allFieldsFilled = false;
                    error.setText("Будь ласка, заповніть всі поля.");
                    break;
                } else {
                    res += (coefs.get(i) / finalSum_coef) * Double.parseDouble(raw_grade);
                }
            }

            if (allFieldsFilled) {
                result.setText(String.valueOf(res));
                error.setText("");
            }
        });

        addSubject.setOnAction(_ -> {
            CreateSubjectScene createSubjectScene = new CreateSubjectScene(primaryStage, semester, specializationName);
            createSubjectScene.show();
        });

        vbox.getChildren().addAll(result, error, continueButton, addSubject);

        Scene input_grades_scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(input_grades_scene);
        primaryStage.show();
    }

    public static void show(Stage primaryStage, int semester, String specializationName) {
        InputGradesScene inputGradesScene = new InputGradesScene(primaryStage, semester, specializationName);
        inputGradesScene.show();
    }
}
