package com.example.mealapp.signup.model;

import com.example.mealapp.login.model.UserCredentials;
import com.example.mealapp.network.NetworkCallBacks;

public interface ISignUpRepository {
    public void signUpUser(NetworkCallBacks networkCallBacks, UserInfo userInfo);
}
