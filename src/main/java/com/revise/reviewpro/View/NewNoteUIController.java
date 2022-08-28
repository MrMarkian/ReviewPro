package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Grading;
import com.revise.reviewpro.Data.Note;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class NewNoteUIController extends Application {

    @FXML
    private  TextField TitleTextInput;

    @FXML
    private  HTMLEditor MainTextInputHTML;

    @FXML
    private  DatePicker ReviewDateInput;

    @FXML
    private ComboBox<Grading> GradingCombo;

    public  TextField getTitleTextInput() {
        return TitleTextInput;
    }

    public  void setTitleTextInput(String titleTextInput) {
        TitleTextInput.setText(titleTextInput);
    }

    public  HTMLEditor getMainTextInputHTML() {
        return MainTextInputHTML;
    }

    public  void setMainTextInputHTML(HTMLEditor mainTextInputHTML) {
        MainTextInputHTML = mainTextInputHTML;
    }

    public DatePicker getReviewDateInput() {
        return ReviewDateInput;
    }

    public void setReviewDateInput(DatePicker reviewDateInput) {
        ReviewDateInput = reviewDateInput;
    }

    public NewNoteUIController() {
    }

    public void SetData(Note noteToView){
        TitleTextInput.setText(noteToView.getTitle());
        MainTextInputHTML.setHtmlText(noteToView.getMainText());
        ReviewDateInput.setValue(noteToView.getReviewDate());
        GradingCombo.setItems(FXCollections.observableList(Arrays.stream(Grading.values()).toList()));
        GradingCombo.setValue(noteToView.getGrade());
    }

    @FXML
    private void initialize() {
        GradingCombo.setItems(FXCollections.observableList(Arrays.stream(Grading.values()).toList()));
        WebView webview = (WebView) MainTextInputHTML.lookup("WebView");
        GridPane.setHgrow(webview, Priority.ALWAYS);
        GridPane.setVgrow(webview, Priority.ALWAYS);
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
            DataRepository.allNotes.add(new Note(GradingCombo.getValue(),MainTextInputHTML.getHtmlText(),ReviewDateInput.getValue(),TitleTextInput.getText()));
            SceneHandler.SwitchScenes(FormWindows.Main);
    }
}
