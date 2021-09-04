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

    static Stage Localstage;

    public static void ShowStage(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ReviewApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        Localstage = stage;

        Localstage.setTitle("RevisionPro!");
        Localstage.setMinWidth(600);
        Localstage.setMinHeight(800);
        Localstage.setScene(scene);
        Localstage.show();
    }
    public static void SwitchScenes(FormWindows windows) throws IOException {

        Parent root;


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
        Localstage.setMinWidth(600);
        Localstage.setMinHeight(800);
        Localstage.setScene(scene);
        Localstage.show();
    }



}
