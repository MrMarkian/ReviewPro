package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Question;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class NewQuestionUIController extends Application {
    MainUIController mainUiController = new MainUIController();

    public NewQuestionUIController() {
    }

    @Override
    public void start(Stage stage) {

    }


    @FXML
    void SaveButtonClicked(ActionEvent action){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Button Clicked");
        alert.setHeaderText("Save Code goes here");
        alert.setContentText("Question Saved");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");

            }
        });
    }

    @FXML
    void CancelButtonClicked (ActionEvent actionEvent) throws IOException {
        SceneHandler.SwitchScenes(FormWindows.Main);
    }


}
