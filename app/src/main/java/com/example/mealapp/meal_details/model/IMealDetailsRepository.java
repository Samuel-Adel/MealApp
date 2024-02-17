package com.example.mealapp.meal_details.model;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.network.IMealDetailsNetworkCallBack;

public interface IMealDetailsRepository {
    public void getMealDetails(IMealDetailsNetworkCallBack mealDetailsNetworkCallBack, String mealId);

    public void addMealToFavourite(Meal meal);
}
