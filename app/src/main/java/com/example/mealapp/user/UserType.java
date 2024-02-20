package com.example.mealapp.user;

public class UserType {
    private UserLoginType userLoginType;
    private static UserType instance = null;

    public static UserType getInstance() {
        if (instance == null) {
            instance = new UserType();
        }
        return instance;

    }

    private UserType() {

    }

    public UserLoginType getCurrentUserType() {
        return userLoginType;

    }

    public void setUserLoginType(UserLoginType userLoginType) {
        this.userLoginType = userLoginType;
    }
}

