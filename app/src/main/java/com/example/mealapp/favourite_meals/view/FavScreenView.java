package com.example.mealapp.favourite_meals.view;

import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavScreenView {
    public void showStoredMeals(Flowable<List<Meal>> meals);

}
