package com.example.mealapp.favourite_meals.presenter;

import com.example.mealapp.home_screen.model.Meal;

import io.reactivex.rxjava3.core.Flowable;

public interface IFavScreenPresenter {

    void removeMealFromFavourite(Meal meal);

    void getAllFavouriteMeals();

    Flowable<Boolean> checkIfFavOrNot(Meal meal);
}
