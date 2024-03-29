package com.example.mealapp.login.presenter;

import com.example.mealapp.login.model.UserCredentials;

public interface ILoginPresenter {
    public void loginWithUserCredentials(UserCredentials userCredentials);
    public void loginWithGoogle(String idToken);
    public void loginAsGuest();
    public void saveUserCredentials(String token);
}
