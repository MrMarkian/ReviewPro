package com.revise.reviewpro;

import com.revise.reviewpro.View.SceneHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class ReviewApp extends Application {

    @Override
    public void start(Stage stage) {

        try {
            SceneHandler.ShowStage(stage);
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void main(String[] args) {
        launch();
    }
}