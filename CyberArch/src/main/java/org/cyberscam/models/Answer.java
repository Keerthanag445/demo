package org.cyberscam.models;

public class Answer {
    private int answerId;
    private int questionId;
    private int userId;
    private String answerText;
    private String postedAt;

    public Answer() {}

    public Answer(int questionId, int userId, String answerText, String postedAt) {
        this.questionId = questionId;
        this.userId = userId;
        this.answerText = answerText;
        this.postedAt = postedAt;
    }

    // Getters and Setters
    public int getAnswerId() {
        return answerId;
    }
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
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
    public String getAnswerText() {
        return answerText;
    }
    public void setAnswerText(String answerText) {
        this.answerText = answerText;

    }
    public String getPostedAt() {
        return postedAt;
    }
    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}
