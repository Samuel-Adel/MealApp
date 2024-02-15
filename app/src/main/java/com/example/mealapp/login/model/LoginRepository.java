package com.example.mealapp.login.model;

import com.example.mealapp.login.network.ILoginRemoteDataSource;
import com.example.mealapp.network.NetworkCallBacks;

public class LoginRepository implements ILoginRepository {
    private ILoginRemoteDataSource loginRemoteDataSource;
    private static LoginRepository instance = null;

    public static LoginRepository getInstance(ILoginRemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new LoginRepository(remoteDataSource);
        }
        return instance;
    }

    private LoginRepository(ILoginRemoteDataSource loginRemoteDataSource) {
        this.loginRemoteDataSource = loginRemoteDataSource;
    }

    @Override
    public void signInUser(NetworkCallBacks networkCallBacks,UserCredentials userCredentials) {
        loginRemoteDataSource.makeNetworkCall(networkCallBacks,userCredentials);
    }
}
