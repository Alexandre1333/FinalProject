package com.fp.finalproject.finalproject;

public class Question {
    private String questionText;
    private String correctAnswer;
    private QuestionType questionType;

    public Question() {
    }

    public Question(String questionText, String correctAnswer, QuestionType questionType) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

}
