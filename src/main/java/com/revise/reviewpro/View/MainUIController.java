package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Grading;
import com.revise.reviewpro.Data.Note;
import com.revise.reviewpro.Data.NotebookController;
import com.revise.reviewpro.Data.Question;
import com.revise.reviewpro.ReviewApp;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import static com.revise.reviewpro.View.SceneHandler.SwitchScenes;

public class MainUIController extends Application {
    @FXML
    private ListView<Note> NoteList;
    @FXML
    private ListView<Question> QuestionList;
    @FXML
    private HTMLEditor NoteEditor;
    @FXML
    private TextField TitleInput;
    @FXML
    private Button SaveButton;
    @FXML
    private ComboBox<Grading> GradingCombo;
    @FXML
    private CheckBox CompleteChecked;
    @FXML
    private DatePicker reviewDateCalender;

    public MainUIController() {

    }

    @FXML
    void ShowNoteFromList(MouseEvent event) {}

    @FXML
    void CompleteClicked(ActionEvent event){}

    @FXML
    void ClearListUIClick(ActionEvent event){DataInterface.allnotes.clear();}

    @FXML
    void SaveButtonClicked(ActionEvent event) {
        System.out.println(GradingCombo.getValue());
        DataInterface.allnotes.add(new Note(Grading.valueOf(GradingCombo.getValue().toString()),NoteEditor.getHtmlText(), reviewDateCalender.getValue(),TitleInput.getText()));
        NoteList.setItems(DataInterface.allnotes);
    }

    @FXML
    void NewNoteButtonClick(ActionEvent event) throws IOException {
        SwitchScenes(event, FormWindows.NewNote);
    }

    @FXML
    void LoadNoteBookMenuClick(ActionEvent event) {

        FileChooser openFileDialog = new FileChooser();
        openFileDialog.setTitle("Location of NoteBook");

        File file = openFileDialog.showOpenDialog(null);

        if (file == null)
            return;

        final boolean b = DataInterface.allnotes.addAll(FXCollections.observableArrayList(NotebookController.LoadNoteBook(file)));
        NoteList.setItems(DataInterface.allnotes);
    }

    @FXML
    void NewNoteMenuClicked(ActionEvent actionEvent) throws IOException {
        SwitchScenes(actionEvent,FormWindows.NewNote);
    }

    @FXML
    void NewQuestionMenuClicked(ActionEvent actionEvent) throws IOException {
        SwitchScenes(actionEvent,FormWindows.Question);
    }

    @FXML
    void SaveNoteBookMenuClick(ActionEvent event) throws IOException {
        FileChooser saveFileDialog = new FileChooser();

        File file = saveFileDialog.showSaveDialog(null);

            if(file != null) {
                NotebookController.SaveNoteBook(DataInterface.allnotes.stream().toList(), file);
            }
    }

    @FXML
    private void initialize() {
        NoteList.getSelectionModel().selectedItemProperty().addListener((observableValue, note, t1) -> {
            TitleInput.setText(t1.getTitle());
            NoteEditor.setHtmlText(t1.getMainText());
            CompleteChecked.setSelected(t1.isComplete());
            reviewDateCalender.setValue(t1.getReviewDate());
            GradingCombo.setValue(t1.getGrade());
        });
        GradingCombo.getItems().setAll(FXCollections.observableArrayList(Grading.values()));
        if ((long) NoteList.getItems().size() > 0)
        {
            SaveButton.setText("Save");
        } else
        {
            SaveButton.setText("New Note");
        }

        WebView webview = (WebView) NoteEditor.lookup("WebView");
        GridPane.setHgrow(webview, Priority.ALWAYS);
        GridPane.setVgrow(webview, Priority.ALWAYS);
        NoteList.setItems(DataInterface.allnotes);
    }


    @Override
    public void start(Stage stage) {}







}
