package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Grading;
import com.revise.reviewpro.Data.Note;
import com.revise.reviewpro.Data.NotebookController;
import com.revise.reviewpro.ReviewApp;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;


public class UIController extends Application {

    @FXML
    private ListView<Note> NoteList;

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

    @FXML
    void populateGradingItems(ActionEvent event) {

    }

    @FXML
    void ShowNoteFromList(MouseEvent event) {

    }

    @FXML
    void CompleteClicked(ActionEvent event){

    }

    @FXML
    void ClearListUIClick(ActionEvent event){
        ReviewApp.allnotes.clear();
    }

    @FXML
    void SaveButtonClicked(ActionEvent event) {

        System.out.println(GradingCombo.getValue());
        ReviewApp.allnotes.add(new Note(Grading.valueOf(GradingCombo.getValue().toString()),NoteEditor.getHtmlText(), reviewDateCalender.getValue(),TitleInput.getText()));
        NoteList.setItems(ReviewApp.allnotes);
    }


    @FXML
    void LoadNoteBookMenuClick(ActionEvent event) throws IOException, ClassNotFoundException {

        FileChooser openFileDialog = new FileChooser();
        openFileDialog.setTitle("Location of NoteBook");

        File file = openFileDialog.showOpenDialog(null);

        if (file == null)
            return;

        final boolean b = ReviewApp.allnotes.addAll(FXCollections.observableArrayList(NotebookController.LoadNoteBook(file)));
        NoteList.setItems(ReviewApp.allnotes);
    }

    @FXML
    void SaveNoteBookMenuClick(ActionEvent event) throws IOException {
        FileChooser saveFileDialog = new FileChooser();

        File file = saveFileDialog.showSaveDialog(null);

            if(file != null) {
                NotebookController.SaveNoteBook(ReviewApp.allnotes.stream().toList(), file);
            }
    }

    @FXML
    private void initialize() {
        NoteList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() {
            @Override
            public void changed(ObservableValue<? extends Note> observableValue, Note note, Note t1) {
                TitleInput.setText(t1.getTitle());
                NoteEditor.setHtmlText(t1.getMainText());
                CompleteChecked.setSelected(t1.isComplete());
                reviewDateCalender.setValue(t1.getReviewDate());
                GradingCombo.setValue(t1.getGrade());
            }
        });
        GradingCombo.getItems().setAll(FXCollections.observableArrayList(Grading.values()));
        if (NoteList.getItems().stream().count() > 0)
        {
            SaveButton.setText("Save");
        } else
        {
            SaveButton.setText("New Note");
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
