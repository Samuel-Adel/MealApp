package com.example.mealapp.meal_details.network;

import com.example.mealapp.home_screen.model.Meal;

public interface IMealDetailsRemoteDataSource {
    public void getMealDetails(IMealDetailsNetworkCallBack mealDetailsNetworkCallBack, String mealId);

}
