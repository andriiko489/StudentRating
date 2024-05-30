package com.example.studentratingnew;

import com.example.studentratingnew.scenes.PickSemesterScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StudentRating");
        PickSemesterScene pickSemesterScene = new PickSemesterScene(primaryStage);
        pickSemesterScene.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
