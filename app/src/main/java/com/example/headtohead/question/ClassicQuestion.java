package com.example.headtohead.question;

import java.util.HashMap;

public class ClassicQuestion extends Question {
    private HashMap<String,Boolean> answers;// Using HashMap To know whether an answer was used or not

    public ClassicQuestion(String question, String typeofquestion, HashMap<String, Boolean> answers,Boolean waseverused) {
        super(question, typeofquestion, waseverused);
        this.answers = answers;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }
}
