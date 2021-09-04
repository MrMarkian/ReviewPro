package com.revise.reviewpro.View;

import com.revise.reviewpro.ReviewApp;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneHandler {

    public static void ShowStage(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ReviewApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("RevisionPro!");
        stage.setMinWidth(600);
        stage.setMinHeight(800);
        stage.setScene(scene);
        stage.show();
    }
    public static void SwitchScenes(Event event, FormWindows windows) throws IOException {
        Stage stage;
        Parent root;

        stage=(Stage) ((Control)(event.getSource())).getScene().getWindow();

        switch (windows){
            case Main -> {
                root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("hello-view.fxml")));
            }

            case Question -> {
                root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("NewQuestion-View.fxml")));
            }
            case NewNote -> {
                root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("NewNote-View.fxml")));
            }
            case Settings -> {
                root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("Settings-view.fxml")));
            }
            default -> throw new IllegalStateException("Unexpected value: " + windows);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
