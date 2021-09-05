package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Note;
import com.revise.reviewpro.Data.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;

public class DataInterface {

    public static final ObservableList<Note> allNotes = FXCollections.observableArrayList();
    public static final ObservableList<Question> allQuestions = FXCollections.observableArrayList();

    public static void AddAllNotesFromList(List<Note> inputList){
        allNotes.setAll(FXCollections.observableList(inputList));
    }

    public static void AddAllQuestionsFromList(List<Question> questions){
        allQuestions.setAll(FXCollections.observableList((questions)));
    }
}
