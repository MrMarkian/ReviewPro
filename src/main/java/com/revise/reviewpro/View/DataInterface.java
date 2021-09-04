package com.revise.reviewpro.View;

import com.revise.reviewpro.Data.Note;
import com.revise.reviewpro.Data.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class DataInterface {

    public static ObservableList<Note> allnotes = FXCollections.observableArrayList();
    public static ObservableList<Question> allQuestions = FXCollections.observableArrayList();
}
