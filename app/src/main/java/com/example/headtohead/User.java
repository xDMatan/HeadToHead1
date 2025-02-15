package com.example.headtohead;

import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private int coins;
    private int thropies;
    private boolean iswaitning;


    public User(int coins, int thropies, String username, String password) {
        this.coins = coins;
        this.thropies = thropies;
        this.username = username;
        this.password = password;
        this.iswaitning= false;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getThropies() {
        return thropies;
    }

    public void setThropies(int thropies) {
        this.thropies = thropies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
