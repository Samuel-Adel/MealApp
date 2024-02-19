package com.example.mealapp.meal_details.view;

import com.example.mealapp.home_screen.model.Meal;

import io.reactivex.rxjava3.core.Flowable;

public interface MealDetailsView {
    public void showMealDetail(Meal meal);

    public void showErrorMessage(String errorMessage);

    public void showOrHideProgressBar(boolean isVisible);

    public void showIfMealISFav(Flowable<Boolean> isFav);

    public void showIfMealIsPlanned(Flowable<Boolean> isPlanned);



}
