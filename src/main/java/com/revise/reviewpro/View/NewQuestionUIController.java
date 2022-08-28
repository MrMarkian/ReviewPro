package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.AnswerType;
import com.revise.reviewpro.Data.Question;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NewQuestionUIController extends Application {
    @FXML
    private TextArea QuestionInput;
    @FXML
    private TextArea AnswerInput;
    @FXML
    private TextArea MultiQuestionInput;
    @FXML
    private TextArea MultiAnswerInput;
    @FXML
    private ListView<String> MultiAnswerList;
    @FXML
    private Button SaveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TabPane AnswerTypeTabPane;
    @FXML
    private TitledPane NotesPane;
    @FXML
    private TitledPane QuestionPane;
    Question currentQuestion;

    public NewQuestionUIController() {}

    public void SetData(Question inQuestion){
        QuestionInput.setText(inQuestion.getQuestionText());
        switch (inQuestion.getAnswerType()){
            case SingleAnswer -> {
                AnswerTypeTabPane.getTabs().remove(1);
            }
            case MultipleChoice -> {
                AnswerTypeTabPane.getTabs().remove(0);
                MultiQuestionInput.setText(inQuestion.getQuestionText());
                MultiAnswerList.setItems(FXCollections.observableList(inQuestion.getPossibleAnswers()));
            }
        }
        currentQuestion = inQuestion;
        SaveButton.setText("Enter Answer");
    }

    private void ValidateQuestion() {
        switch (currentQuestion.getAnswerType()){
            case SingleAnswer -> {
                if(currentQuestion.ValidateSingleAnswerQuestion(AnswerInput.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You guessed Corrrectly");
                    alert.setTitle("Party Time!");
                    alert.show();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "You guessed Incorrectly");
                    alert.setTitle("Idiot");
                    alert.show();
                }
            }
            case MultipleChoice -> {

            }
            default -> throw new IllegalStateException("Unexpected value: " + currentQuestion.getAnswerType());
        }
    }

    @FXML
    public void AddExtraAnswer(ActionEvent event){
        if (!Objects.equals(MultiAnswerInput.getText(), ""))
        MultiAnswerList.getItems().add(MultiAnswerInput.getText());
    }

    @Override
    public void start(Stage stage) {
        System.out.println(" New Question constructor called");
    }

    @FXML
    void SaveButtonClicked(ActionEvent action) throws IOException {

        if (SaveButton.getText().equals("Enter Answer")) {
            ValidateQuestion();
            return;
        }
        switch (AnswerTypeTabPane.getSelectionModel().getSelectedItem().getText()) {
            case "Single Answer Question" -> {
                System.out.println("Single Answer");
                Question questionToSave = new Question(QuestionInput.getText(), AnswerInput.getText(), null, AnswerType.SingleAnswer);
                DataRepository.allQuestions.add(questionToSave);
                SceneHandler.SwitchScenes(FormWindows.Main);
            }
            case "Multiple Choice" -> {
                System.out.println("Multi Choice");
                Question multiQuestionToSave = new Question(MultiQuestionInput.getText(), AnswerInput.getText(), MultiAnswerList.getItems().stream().toList(), AnswerType.MultipleChoice);
                DataRepository.allQuestions.add(multiQuestionToSave);
                SceneHandler.SwitchScenes(FormWindows.Main);
            }
            default -> throw new IllegalStateException("Unexpected value: " + AnswerTypeTabPane.getSelectionModel().getSelectedItem().getText());
        }
    }

    @FXML
    void CancelButtonClicked (ActionEvent actionEvent) throws IOException {
        SceneHandler.SwitchScenes(FormWindows.Main);
    }
}
