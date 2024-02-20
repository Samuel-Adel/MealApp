package com.example.mealapp.signup.view;

public interface SignUpView {
    public void showErrorMessage(String errorMessage);

    public void signedUpSuccessfully(String signedUpSuccessfully);

    public void statusBarVisibilityStatus(boolean isVisible);

}
