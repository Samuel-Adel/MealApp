package com.example.mealapp.meal_plans.view;

import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealPlansView {
    public void showRandomMeal(Meal reandomMeal);
    public void showFailureMsg(String failureMsg);
    public void showProgressBar(boolean status);

    public void showSaturdayPlanMeals(Flowable<List<Meal>> saturdayMeal);

    public void showSundayPlanMeals(Flowable<List<Meal>> sundayMeals);

    public void showMondayPlanMeals(Flowable<List<Meal>> mondayMeals);

    public void showTuesdayPlanMeals(Flowable<List<Meal>> tuesdayMeals);

    public void showWednesdayPlanMeals(Flowable<List<Meal>> wednesdayMeals);

    public void showThursdayPlanMeals(Flowable<List<Meal>> thursdayMeals);

    public void showFridayPlanMeals(Flowable<List<Meal>> fridayMeals);

}
