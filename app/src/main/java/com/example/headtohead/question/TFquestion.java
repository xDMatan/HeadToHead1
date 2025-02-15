package com.example.headtohead.question;

public class TFquestion extends Question {
    private boolean answer;

    public TFquestion(String question, String typeofquestion, boolean answer,Boolean waseverused) {
        super(question, typeofquestion,waseverused);
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }
}
