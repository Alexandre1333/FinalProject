package com.fp.finalproject.finalproject;

import java.util.LinkedList;

public class MCQuestion extends Question {

    private LinkedList<String> options;

    public MCQuestion() {
        super();
        this.options = new LinkedList<>();
    }

    public MCQuestion(LinkedList<String> optionsParam) {
        super();
        this.options = new LinkedList<>();
        this.options.addAll(optionsParam);
    }

    public MCQuestion(String questionText, String correctAnswer, QuestionType questionType, LinkedList<String> optionsParam) {
        super(questionText, correctAnswer, questionType);
        this.options = new LinkedList<>();
        this.options.addAll(optionsParam);
    }

    public LinkedList<String> getOptions() {
        return options;
    }
    public void setOptions(LinkedList<String> optionsParam) {
        this.options = new LinkedList<>();
    }
}
