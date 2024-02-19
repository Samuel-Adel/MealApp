package com.example.mealapp.login.network;

import com.example.mealapp.login.model.UserCredentials;
import com.example.mealapp.network.NetworkCallBacks;

public interface ILoginRemoteDataSource {
    void makeNetworkCall(NetworkCallBacks networkCallBacks, UserCredentials userCredentials);
    void signUpWithGoogle(NetworkCallBacks networkCallBacks);
}
