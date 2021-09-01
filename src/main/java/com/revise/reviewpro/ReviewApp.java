package com.revise.reviewpro;

import com.revise.reviewpro.Data.Note;
import com.revise.reviewpro.Data.NotebookController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ReviewApp extends Application {

    public static ObservableList<Note> allnotes = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReviewApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("RevisionPro!");
        stage.setMinWidth(600);
        stage.setMinHeight(800);
        stage.setScene(scene);
        stage.show();
        try {
            LoadStartList();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void LoadStartList() throws IOException, ClassNotFoundException {
        try {
            allnotes.addAll(Objects.requireNonNull(NotebookController.LoadNoteBook(new File("C:/test.txt"))));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        launch();
    }
}