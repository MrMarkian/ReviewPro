package com.revise.reviewpro.Data;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private String QuestionText;
    private String correctSingleAnswer;
    private List<String> correctMultiAnswers;
    private List<String> possibleAnswers;
    private AnswerType answerType;

    public Question(String questionText, String correctSingleAnswer, List<String> possibleAnswers, AnswerType answerType) {
        QuestionText = questionText;
        this.correctSingleAnswer = correctSingleAnswer;
        this.possibleAnswers = possibleAnswers;
        this.answerType = answerType;
      }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public String getCorrectSingleAnswer() {
        return correctSingleAnswer;
    }

    public void setCorrectSingleAnswer(String correctSingleAnswer) {
        this.correctSingleAnswer = correctSingleAnswer;
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


    public boolean ValidateSingleAnswerQuestion(Object answers){
        switch (this.answerType){
            case SingleAnswer ->{
                return correctSingleAnswer.equalsIgnoreCase((String) answers);
            }

            case MultipleChoice -> {
                return correctMultiAnswers.equals(answers);
            }
            default -> {
                return false;
            }
        }
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
