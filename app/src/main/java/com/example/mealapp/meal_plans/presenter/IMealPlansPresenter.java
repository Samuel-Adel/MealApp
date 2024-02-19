package com.example.mealapp.meal_plans.presenter;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_plans.model.Days;

public interface IMealPlansPresenter {
    public void getRandomMeal();

    public void getSaturdayPlanMeals();

    public void getSundayPlanMeals();

    public void getMondayPlanMeals();

    public void getTuesdayPlanMeals();

    public void getWednesdayPlanMeals();

    public void getThursdayPlanMeals();

    public void getFridayPlanMeals();

    public void addMealToFav(Meal meal);

    public void removeMealFromPlan(Meal meal);
}
