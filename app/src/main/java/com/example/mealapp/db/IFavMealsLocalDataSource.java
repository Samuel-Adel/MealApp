package com.example.mealapp.db;


import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public interface IFavMealsLocalDataSource {
    void insertMeal(Meal product);

    void deleteMeal(Meal product);

    Flowable<List<Meal>> getAllMeals();
}
