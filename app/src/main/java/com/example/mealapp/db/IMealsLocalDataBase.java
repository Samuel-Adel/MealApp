package com.example.mealapp.db;


import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_plans.model.Days;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public interface IMealsLocalDataBase {
    void insertFavMeal(Meal meal);
    void logOut();

    void insertMealPlan(Meal meal, Days day);

    void deleteFavMeal(Meal product);

    void deletePlannedMeal(Meal product);


    Flowable<List<Meal>> getFavMeals();
    Flowable<List<Meal>> getAllPlannedMeals();

    Flowable<Boolean> isFavMeal(String mealId);

    Flowable<Boolean> isPlannedMeal(String mealDay);

    Flowable<List<Meal>> getPlannedMealsByDay(String dayName);

}
