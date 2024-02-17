package com.example.mealapp.meal_details.presenter;

import com.example.mealapp.home_screen.model.Meal;

public interface IMealDetailsPresenter {
    public void getMealDetails(String mealId);

    public void addMealToFav(Meal meal);
    public void addMealToPlan(Meal meal);
}
