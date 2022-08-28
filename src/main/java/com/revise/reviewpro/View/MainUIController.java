package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Grading;
import com.revise.reviewpro.Data.Note;
import com.revise.reviewpro.Data.NotebookController;
import com.revise.reviewpro.Data.Question;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.revise.reviewpro.View.SceneHandler.SwitchScenes;
import static com.revise.reviewpro.View.SceneHandler.SwtichViewPane;

public class MainUIController extends Application {

    public MenuItem BootstrapClicked;
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
    @FXML
    private Button NewNoteButton;
    @FXML
    private Accordion AccordianDividers;
    @FXML
    private AnchorPane ViewAnchorPane;
    @FXML
    private SplitPane MainSplitPane;

    public MainUIController() {
    }

    @FXML
    void ShowNoteFromList(MouseEvent event) {}

    @FXML
    void DarkThemeMenuClicked(ActionEvent event){
        SceneHandler.SwitchThemes(ThemeStyle.Dark);
    }
    @FXML
    void onBootstrapClicked(ActionEvent event){
        SceneHandler.SwitchThemes(ThemeStyle.Light);
    }

    @FXML
    void CompleteClicked(ActionEvent event){}

    @FXML
    void ClearListUIClick(ActionEvent event){
        DataRepository.allNotes.clear();}

    @FXML
    void CloseMenuClicked(ActionEvent event){
        Platform.exit();
    }
    @FXML
    void SaveButtonClicked(ActionEvent event) {
        System.out.println(GradingCombo.getValue());
        DataRepository.allNotes.add(new Note(Grading.valueOf(GradingCombo.getValue().toString()),NoteEditor.getHtmlText(), reviewDateCalender.getValue(),TitleInput.getText()));
        NoteList.setItems(DataRepository.allNotes);
    }

    @FXML
    void NewNoteButtonClick(ActionEvent event) throws IOException {
        SwitchScenes(FormWindows.NewNote);
    }

    @FXML
    void LoadNoteBookMenuClick(ActionEvent event) {

        FileChooser openFileDialog = new FileChooser();
        openFileDialog.setTitle("Location of NoteBook");

        File file = openFileDialog.showOpenDialog(null);

        if (file == null)
            return;
        try {
            NotebookController.LoadNoteBook(file);

            NoteList.setItems(DataRepository.allNotes);
        }catch (Exception ex){
            Alert al = new Alert(Alert.AlertType.ERROR, "Unable to load Notebook, File Corruption or incompatible version");
            al.show();
        }
    }

    @FXML
    void NewNoteMenuClicked(ActionEvent actionEvent) throws IOException {
        SwitchScenes(FormWindows.NewNote);
    }

    @FXML
    void NewQuestionMenuClicked(ActionEvent actionEvent) throws IOException {
        SwitchScenes(FormWindows.Question);
    }

    @FXML
    void SaveNoteBookMenuClick(ActionEvent event) throws IOException {
        FileChooser saveFileDialog = new FileChooser();

        File file = saveFileDialog.showSaveDialog(null);

            if(file != null) {
                NotebookController.SaveNoteBook(file);
            }
    }

    @FXML
    void EncryptNoteBookMenuClick(ActionEvent event){
        PasswordDialog pd = new PasswordDialog();
        Optional<String> result = pd.showAndWait();
            if(result.isPresent()){
                NotebookController.EncodeNoteBook(result.toString());
                NoteList.refresh();
                QuestionList.refresh();
                RefreshUI();
            }
    }

    @FXML
    void DecryptNoteBookMenuClick(ActionEvent event){
        PasswordDialog pd = new PasswordDialog();
        Optional<String> result = pd.showAndWait();
        if(result.isPresent()){
            NotebookController.DecodeNoteBook(result.toString());
            NoteList.refresh();
            QuestionList.refresh();
            RefreshUI();
        }
    }

    private void RefreshUI(){
        Note t1 = NoteList.getSelectionModel().getSelectedItem();
        TitleInput.setText(t1.getTitle());
        NoteEditor.setHtmlText(t1.getMainText());
        CompleteChecked.setSelected(t1.isComplete());
        reviewDateCalender.setValue(t1.getReviewDate());
        GradingCombo.setValue(t1.getGrade());

    }

    @FXML
    private void initialize() {
        NoteList.getSelectionModel().selectedItemProperty().addListener((observableValue, note, t1) -> {
            SwtichViewPane(FormWindows.NewNote,MainSplitPane,t1);

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
        NoteList.setItems(DataRepository.allNotes);

        QuestionList.setItems(DataRepository.allQuestions);
        QuestionList.getSelectionModel().selectedItemProperty().addListener((observableValue, question, t1) -> SwtichViewPane(FormWindows.Question,MainSplitPane,t1));
    }


    @Override
    public void start(Stage stage) {}

}
