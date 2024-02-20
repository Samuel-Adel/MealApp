package com.example.mealapp.meal_plans.network;

import com.example.mealapp.home_screen.model.Meal;

public interface IMealPlansNetworkCallBacks {
    public void onSuccessfulCallBack(Meal meal);
    public void onFailureResult(String failureMsg);
    public void callInProgressStatus(boolean status);
}
