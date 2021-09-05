package com.revise.reviewpro.View;

import com.revise.reviewpro.ReviewApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneHandler {

    static Stage LocalStage;

    public static void ShowStage(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ReviewApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        LocalStage = stage;

        LocalStage.setTitle("RevisionPro!");
        LocalStage.setMinWidth(600);
        LocalStage.setMinHeight(800);
        LocalStage.setScene(scene);
        LocalStage.show();
    }
    public static void SwitchScenes(FormWindows windows) throws IOException {

        Parent root;

        switch (windows){
            case Main -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("hello-view.fxml")));
            case Question -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("NewQuestion-View.fxml")));
            case NewNote -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("NewNote-View.fxml")));
            case Settings -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("Settings-view.fxml")));
            default -> throw new IllegalStateException("Unexpected value: " + windows);
        }

        Scene scene = new Scene(root);
        LocalStage.setMinWidth(600);
        LocalStage.setMinHeight(800);
        LocalStage.setScene(scene);
        LocalStage.show();
    }



}
