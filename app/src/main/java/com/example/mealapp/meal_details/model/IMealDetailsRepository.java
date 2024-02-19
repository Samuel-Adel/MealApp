package com.example.mealapp.meal_details.model;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.network.IMealDetailsNetworkCallBack;
import com.example.mealapp.meal_plans.model.Days;

import io.reactivex.rxjava3.core.Flowable;

public interface IMealDetailsRepository {
    public void getMealDetails(IMealDetailsNetworkCallBack mealDetailsNetworkCallBack, String mealId);

    public void addMealToFavourite(Meal meal);
    public void removeMealFromFavourite(Meal meal);
    public void addMealToPlan(Meal meal, Days day);
    public void removeMealFromPlan(Meal meal);
    public Flowable<Boolean> isMealFav(Meal meal);
    public Flowable<Boolean>  isMealPlanned(Meal meal);
}
