package com.example.mealapp.signup.model;

import com.example.mealapp.network.NetworkCallBacks;
import com.example.mealapp.signup.network.ISignUpRemoteDataSource;

public class SignUpRepository implements ISignUpRepository{
    private ISignUpRemoteDataSource signUpRemoteDataSource;
    private static SignUpRepository instance=null;


    public static SignUpRepository getInstance(ISignUpRemoteDataSource remoteDataSource){
        if(instance==null){
            instance=new SignUpRepository(remoteDataSource);
        }
        return instance;
    }
    private SignUpRepository(ISignUpRemoteDataSource remoteDataSource) {
        this.signUpRemoteDataSource=remoteDataSource;
    }

    @Override
    public void signUpUser(NetworkCallBacks networkCallBacks, UserInfo userInfo) {
        signUpRemoteDataSource.makeNetworkCall(networkCallBacks,userInfo);
    }
}
