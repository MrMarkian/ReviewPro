package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Grading;
import com.revise.reviewpro.Data.Note;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.io.IOException;

public class NewNoteUIController extends Application {

    @FXML
    TextField TitleTextInput;

    @FXML
    HTMLEditor MainTextInputHTML;

    @FXML
    DatePicker ReviewDateInput;

    public NewNoteUIController() {
    }

    @Override
    public void start(Stage stage) {

    }

    @FXML
    public void CancelButtonClicked(ActionEvent event) throws IOException {
        SceneHandler.SwitchScenes(FormWindows.Main);
    }

    @FXML
    public void SaveButtonClicked(ActionEvent event) throws IOException {
            DataInterface.allnotes.add(new Note(Grading.AVERAGE,MainTextInputHTML.getHtmlText(),ReviewDateInput.getValue(),TitleTextInput.getText()));
            SceneHandler.SwitchScenes(FormWindows.Main);
    }

}
