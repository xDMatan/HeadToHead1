package com.example.headtohead.question;

public class Question {
    private String question;
    private String typeofquestion;
    private boolean waseverused;

    public Question(String question, String typeofquestion,Boolean waseverused) {
        this.question = question;
        this.typeofquestion = typeofquestion;
        this.waseverused = false;
    }

    public void setWaseverused(boolean waseverused) {
        this.waseverused = waseverused;
    }

    public boolean getWaseverused() {
        return waseverused;
    }

    public String getQuestion() {
        return question;
    }

    public String getTypeofquestion() {
        return typeofquestion;
    }

}
