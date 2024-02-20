package com.example.mealapp.signup.network;

import com.example.mealapp.network.NetworkCallBacks;
import com.example.mealapp.signup.model.UserInfo;

public interface ISignUpRemoteDataSource {
    void makeNetworkCall(NetworkCallBacks networkCallBacks, UserInfo userInfo);

}
