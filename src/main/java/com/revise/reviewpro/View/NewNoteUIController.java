package com.revise.reviewpro.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class NewNoteUIController extends Application {



    public NewNoteUIController() {
    }

    @Override
    public void start(Stage stage) {

    }

    @FXML
    public void CancelButtonClicked(ActionEvent event) throws IOException {
        SceneHandler.SwitchScenes(event, FormWindows.Main);
    }

    @FXML
    public void SaveButtonClicked(ActionEvent event){

    }

}
