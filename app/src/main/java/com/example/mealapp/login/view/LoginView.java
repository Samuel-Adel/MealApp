package com.example.mealapp.login.view;

public interface LoginView {

    public void loggedInSuccessfully(String loggedInSuccessfullyMessage);
    public void showErrorMessage(String errorMessage);

    public void statusBarVisibilityStatus(boolean isVisible);
}
