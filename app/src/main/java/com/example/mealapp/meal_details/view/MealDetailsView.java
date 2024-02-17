package com.example.mealapp.meal_details.view;

import com.example.mealapp.home_screen.model.Meal;

public interface MealDetailsView {
    public void showMealDetail(Meal meal);
    public void showErrorMessage(String errorMessage);
    public void showOrHideProgressBar(boolean isVisible);

}
