package com.example.mealapp.meal_details.presenter;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_plans.model.Days;

public interface IMealDetailsPresenter {
    public void getMealDetails(String mealId);

    public void addMealToFav(Meal meal);
    public void removeMealFromFav(Meal meal);

    public void addMealToPlan(Meal meal, Days day);
    public void removeMealFromPlan(Meal meal);

    public void checkIfMealIsFav(Meal meal);

    public void checkIfMealIsPlanned(Meal meal);
}
