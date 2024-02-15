package com.example.mealapp.login.model;

import com.example.mealapp.network.NetworkCallBacks;

public interface ILoginRepository {
    public void signInUser(NetworkCallBacks networkCallBacks,UserCredentials userCredentials);
}
