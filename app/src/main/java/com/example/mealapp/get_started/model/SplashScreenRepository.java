package com.example.mealapp.get_started.model;

import com.example.mealapp.user.UserSavedCredentialsManager;

public class SplashScreenRepository implements ISplashScreenRepository {
    private UserSavedCredentialsManager userSavedCredentialsManager;
    private static SplashScreenRepository instance = null;

    public static SplashScreenRepository getInstance(UserSavedCredentialsManager userSavedCredentialsManager) {
        if (instance == null) {
            instance = new SplashScreenRepository(userSavedCredentialsManager);
        }
        return instance;
    }

    private SplashScreenRepository(UserSavedCredentialsManager userSavedCredentialsManager) {
        this.userSavedCredentialsManager = userSavedCredentialsManager;
    }

    @Override
    public String checkUserSavedToken() {
        return userSavedCredentialsManager.getUserToken();
    }
}
