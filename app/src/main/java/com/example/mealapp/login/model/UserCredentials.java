package com.example.mealapp.login.model;

public class UserCredentials {
    public static final String googleLogin = "1017035063127-s2t15996ln6i43aqfqfht3or1o3pog3p.apps.googleusercontent.com";
    private String userEmail;
    private String password;

    public UserCredentials(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
