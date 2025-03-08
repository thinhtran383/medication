package com.example.medicine.util;

public class UserContext {
    private static UserContext instance;
    private String username;

    private UserContext() {
    }

    public static UserContext getInstance() {
        if (instance == null) {
            instance = new UserContext();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clear() {
        username = null;
    }
}
