package com.example.mealapp.favourite_meals.presenter;

import com.example.mealapp.home_screen.model.Meal;

public interface IFavScreenPresenter {

    void removeMealFromFavourite(Meal meal);

    void getAllFavouriteMeals();
}
