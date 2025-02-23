package org.cyberscam.models;

public class Question {
    private int questionId;
    private int userId;
    private String questionText;
    private String postedAt;

    public Question() {}

    public Question(int userId, String questionText, String postedAt) {
        this.userId = userId;
        this.questionText = questionText;
        this.postedAt = postedAt;
    }


    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public String getPostedAt() {
        return postedAt;
    }
    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}
