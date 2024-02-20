package com.example.mealapp.login.model;

public class UserCredentials {
    public static final String googleLogin = "565663875855-eqr7r31mfji9h7ug69v3helmbg7pod4g.apps.googleusercontent.com";
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
