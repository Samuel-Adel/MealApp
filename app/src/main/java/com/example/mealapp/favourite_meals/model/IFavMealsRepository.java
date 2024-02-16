package com.example.mealapp.favourite_meals.model;

import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface IFavMealsRepository {
    public void deleteMeal(Meal product);

    public Flowable<List<Meal>> getStoredMeals();
}
