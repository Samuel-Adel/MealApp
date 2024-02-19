package com.example.mealapp.meal_plans.model;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_plans.network.IMealPlansNetworkCallBacks;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface IMealPlansRepository {
    public void getRandomMeal(IMealPlansNetworkCallBacks mealPlansNetworkCallBacks);

    public Flowable<List<Meal>> getSaturdayPlanMeals();

    public Flowable<List<Meal>>  getSundayPlanMeals();

    public Flowable<List<Meal>>  getMondayPlanMeals();

    public Flowable<List<Meal>>  getTuesdayPlanMeals();

    public Flowable<List<Meal>>  getWednesdayPlanMeals();

    public Flowable<List<Meal>>  getThursdayPlanMeals();

    public Flowable<List<Meal>>  getFridayPlanMeals();

    public void addMealToFav(Meal meal);

    public void removeMealFromPlan(Meal meal);
}
