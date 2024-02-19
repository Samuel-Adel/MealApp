package com.example.mealapp.login.model;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSavedCredentialsManager {
    private static final String PREF_NAME = "UserTokenPrefs";
    private static final String KEY_USER_TOKEN = "userToken";

    private SharedPreferences sharedPreferences;
    private static UserSavedCredentialsManager instance = null;

    public static UserSavedCredentialsManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserSavedCredentialsManager(context);
        }
        return instance;
    }

    private UserSavedCredentialsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_TOKEN, token);
        editor.apply();
    }

    public String getUserToken() {
        return sharedPreferences.getString(KEY_USER_TOKEN, null);
    }

    public void clearUserToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER_TOKEN);
        editor.apply();
    }
}
