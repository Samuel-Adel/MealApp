package com.example.mealapp.login.model;

import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.login.network.ILoginRemoteDataSource;
import com.example.mealapp.network.NetworkCallBacks;
import com.example.mealapp.user.UserLoginType;
import com.example.mealapp.user.UserSavedCredentialsManager;
import com.example.mealapp.user.UserType;

public class LoginRepository implements ILoginRepository {
    private UserType userType;
    private ILoginRemoteDataSource loginRemoteDataSource;
    private UserSavedCredentialsManager userSavedCredentialsManager;
    private static LoginRepository instance = null;
    private IMealsLocalDataBase mealsLocalDataBase;

    public static LoginRepository getInstance(ILoginRemoteDataSource remoteDataSource, UserSavedCredentialsManager userSavedCredentialsManager) {
        if (instance == null) {
            instance = new LoginRepository(remoteDataSource, userSavedCredentialsManager);
        }
        return instance;
    }

    private LoginRepository(ILoginRemoteDataSource loginRemoteDataSource, UserSavedCredentialsManager userSavedCredentialsManager) {
        this.loginRemoteDataSource = loginRemoteDataSource;
        this.userSavedCredentialsManager = userSavedCredentialsManager;
        this.userType = UserType.getInstance();
    }

    @Override
    public void signInUser(NetworkCallBacks networkCallBacks, UserCredentials userCredentials) {
        userType.setUserLoginType(UserLoginType.AuthenticatedUser);
        loginRemoteDataSource.makeNetworkCall(networkCallBacks, userCredentials);
    }

    @Override
    public void signInUserAsGuest() {
        userType.setUserLoginType(UserLoginType.guestUser);
    }

    @Override
    public void saveUserCredentials(String token) {
        userSavedCredentialsManager.saveUserToken(token);
    }
}
