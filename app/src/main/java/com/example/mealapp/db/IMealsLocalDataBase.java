package com.example.mealapp.db;


import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public interface IMealsLocalDataBase {
    void insertMeal(Meal product);

    void deleteMeal(Meal product);

    Flowable<List<Meal>> getAllMeals();
    Flowable<List<Meal>> getFavMeals();
    Flowable<Boolean> isFavMeal(String mealId);

    Flowable<List<Meal>> getSaturdayMeals();
    Flowable<List<Meal>> getSundayMeals();

    Flowable<List<Meal>> getMondayMeals();

    Flowable<List<Meal>> getTuesdayMeals();

    Flowable<List<Meal>> getWednesdayMeals();

    Flowable<List<Meal>> getThursdayMeals();

    Flowable<List<Meal>> getFridayMeals();

}
