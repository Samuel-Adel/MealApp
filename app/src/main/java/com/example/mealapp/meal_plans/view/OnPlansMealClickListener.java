package com.example.mealapp.meal_plans.view;

import com.example.mealapp.home_screen.model.Meal;

public interface OnPlansMealClickListener {
    public void showMealDetails(Meal meal);
    public void onRemoveMealButtonClicked(Meal meal);
}
