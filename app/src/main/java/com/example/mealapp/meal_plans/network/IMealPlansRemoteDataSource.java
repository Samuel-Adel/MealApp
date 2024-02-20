package com.example.mealapp.meal_plans.network;

public interface IMealPlansRemoteDataSource {
    public void getRandomMeal(IMealPlansNetworkCallBacks mealPlansNetworkCallBacks);
}
