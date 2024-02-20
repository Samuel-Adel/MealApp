package com.example.mealapp.meal_details.network;

import com.example.mealapp.home_screen.model.Meal;

public interface IMealDetailsNetworkCallBack {
    public void onSuccessfulResult(Meal meal);
    public void onFailureResult(String failureMsg);
    public void callInProgressStatus(boolean status);
}
