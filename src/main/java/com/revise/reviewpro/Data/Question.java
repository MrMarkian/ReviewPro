package com.revise.reviewpro.Data;

import javafx.scene.Group;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class Question {

    private String QuestionText;
    private String correctAnswer;

    private List<String> possibleAnswers;
    private AnswerType answerType;

    private Boolean timed;
    private LocalDateTime answerTime;

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public Boolean getTimed() {
        return timed;
    }

    public void setTimed(Boolean timed) {
        this.timed = timed;
    }

    public LocalDateTime getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(LocalDateTime answerTime) {
        this.answerTime = answerTime;
    }

    public Group RenderQuestionUI (){
        //todo: Add question renderer
        return new Group();

    }
}
