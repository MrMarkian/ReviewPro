package com.revise.reviewpro.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Note implements Serializable {

    private int ID;
    private Grading grade;
    private String MainText;
    private LocalDate ReviewDate;
    private String title;
    private boolean complete;

    public Note(Grading grade, String mainText, LocalDate entrydate, String title) {
        this.grade = grade;
        MainText = mainText;
        this.ReviewDate = entrydate;
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title + " - " + grade.toString() + " - " + this.ReviewDate.toString();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Grading getGrade() {
        return grade;
    }

    public void setGrade(Grading grade) {
        this.grade = grade;
    }

    public String getMainText() {
        return MainText;
    }

    public void setMainText(String mainText) {
        MainText = mainText;
    }

    public LocalDate getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.ReviewDate = reviewDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
