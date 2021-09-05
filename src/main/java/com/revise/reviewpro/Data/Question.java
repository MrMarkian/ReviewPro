package com.revise.reviewpro.Data;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private String QuestionText;
    private String correctAnswer;

    private List<String> possibleAnswers;
    private AnswerType answerType;


    public Question(String questionText, String correctAnswer, List<String> possibleAnswers, AnswerType answerType) {
        QuestionText = questionText;
        this.correctAnswer = correctAnswer;
        this.possibleAnswers = possibleAnswers;
        this.answerType = answerType;
      }

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

    @Override
    public String toString() {
        String Result = QuestionText ;
        if (answerType == AnswerType.SingleAnswer)
            Result += " - Single";
        if (answerType == AnswerType.MultipleChoice)
            Result += " - Multi";
        return Result;
    }
}
