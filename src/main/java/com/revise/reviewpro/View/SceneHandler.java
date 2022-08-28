package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Note;
import com.revise.reviewpro.Data.Question;
import com.revise.reviewpro.ReviewApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneHandler {

    static Stage LocalStage;
    static ThemeStyle currentTheme;

    public static void ShowStage(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ReviewApp.class.getResource("MainWindow.fxml"));
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
            case Main -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("MainWindow.fxml")));
            case Question -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("NewQuestion-View.fxml")));
            case NewNote -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("NewNote-View.fxml")));
            case Settings -> root = FXMLLoader.load(Objects.requireNonNull(ReviewApp.class.getResource("Settings-view.fxml")));
            default -> throw new IllegalStateException("Unexpected value: " + windows);
        }

        Scene scene = new Scene(root);
        LocalStage.setMinWidth(600);
        LocalStage.setMinHeight(800);
        LocalStage.setScene(scene);
        SwitchThemes(getCurrentTheme());
        LocalStage.show();
    }

    public static void SwitchThemes(ThemeStyle theme ){
        String cssFileName;
        currentTheme = theme;

        switch (theme)
        {
            case Light -> {
                cssFileName = "Bootsrap.css";
            }
            case Dark -> {
                cssFileName = "DarkTheme.css";
            }
            default -> throw new IllegalStateException("Unexpected value: " + theme);
        }
        LocalStage.getScene().getStylesheets().add(Objects.requireNonNull(ReviewApp.class.getResource(cssFileName)).toExternalForm());
    }


    public static void SwtichViewPane(FormWindows window, SplitPane renderTarget, Object passedObject) {
        switch (window) {
            case Question -> {
                Node componentsPane;

                try {
                    componentsPane = renderTarget.getItems().get(1);
                    renderTarget.getItems().remove(componentsPane);
                    FXMLLoader fxmlLoader = new FXMLLoader(
                            Objects.requireNonNull(ReviewApp.class.getResource("NewQuestion-View.fxml"))
                    );

                    AnchorPane anchorPane = fxmlLoader.load();
                    NewQuestionUIController newQuestionUIController = fxmlLoader.getController();
                    newQuestionUIController.SetData((Question) passedObject);

                    renderTarget.getItems().add(1, anchorPane);
                    SceneHandler.SwitchThemes(SceneHandler.getCurrentTheme());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case NewNote -> {
                Node componentsPane;
                componentsPane = renderTarget.getItems().get(1);
                renderTarget.getItems().remove(componentsPane);

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(
                            Objects.requireNonNull(ReviewApp.class.getResource("NewNote-View.fxml"))
                    );
                    AnchorPane anchorPane = fxmlLoader.load();
                    NewNoteUIController newNoteUIController = fxmlLoader.getController();
                    newNoteUIController.SetData((Note) passedObject);

                    renderTarget.getItems().add(1, anchorPane);
                    SceneHandler.SwitchThemes(SceneHandler.getCurrentTheme());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ThemeStyle getCurrentTheme() {
        return currentTheme;
    }
}
