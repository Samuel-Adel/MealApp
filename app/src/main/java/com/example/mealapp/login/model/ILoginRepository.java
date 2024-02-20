package com.example.mealapp.login.model;

import com.example.mealapp.network.NetworkCallBacks;

public interface ILoginRepository {
    public void signInUser(NetworkCallBacks networkCallBacks,UserCredentials userCredentials);
    public void signInUserWithGoogle(NetworkCallBacks networkCallBacks, String idToken);
    public void signInUserAsGuest();
    public void saveUserCredentials(String token);
}
