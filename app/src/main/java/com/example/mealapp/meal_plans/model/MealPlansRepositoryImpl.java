package com.example.mealapp.meal_plans.model;

import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_plans.network.IMealPlansNetworkCallBacks;
import com.example.mealapp.meal_plans.network.IMealPlansRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealPlansRepositoryImpl implements IMealPlansRepository {
    private final IMealPlansRemoteDataSource mealPlansRemoteDataSource;
    private final IMealsLocalDataBase mealsLocalDataBase;
    private static MealPlansRepositoryImpl instance = null;

    public static MealPlansRepositoryImpl getInstance(IMealPlansRemoteDataSource remoteDataSource, IMealsLocalDataBase mealsLocalDataSource) {
        if (instance == null) {
            instance = new MealPlansRepositoryImpl(remoteDataSource, mealsLocalDataSource);
        }
        return instance;
    }

    private MealPlansRepositoryImpl(IMealPlansRemoteDataSource remoteDataSource, IMealsLocalDataBase mealsLocalDataBase) {
        this.mealPlansRemoteDataSource = remoteDataSource;
        this.mealsLocalDataBase = mealsLocalDataBase;
    }

    @Override
    public void getRandomMeal(IMealPlansNetworkCallBacks mealPlansNetworkCallBacks) {
        mealPlansRemoteDataSource.getRandomMeal(mealPlansNetworkCallBacks);
    }

    @Override
    public Flowable<List<Meal>> getSaturdayPlanMeals() {
        return mealsLocalDataBase.getPlannedMealsByDay(Days.saturday.name());
    }

    @Override
    public Flowable<List<Meal>> getSundayPlanMeals() {
        return mealsLocalDataBase.getPlannedMealsByDay(Days.sunday.name());
    }

    @Override
    public Flowable<List<Meal>> getMondayPlanMeals() {
        return mealsLocalDataBase.getPlannedMealsByDay(Days.monday.name());
    }

    @Override
    public Flowable<List<Meal>> getTuesdayPlanMeals() {
        return mealsLocalDataBase.getPlannedMealsByDay(Days.tuesday.name());
    }

    @Override
    public Flowable<List<Meal>> getWednesdayPlanMeals() {
        return mealsLocalDataBase.getPlannedMealsByDay(Days.wednesday.name());
    }

    @Override
    public Flowable<List<Meal>> getThursdayPlanMeals() {
        return mealsLocalDataBase.getPlannedMealsByDay(Days.thursday.name());
    }

    @Override
    public Flowable<List<Meal>> getFridayPlanMeals() {
        return mealsLocalDataBase.getPlannedMealsByDay(Days.friday.name());
    }

    @Override
    public void addMealToFav(Meal meal) {
        mealsLocalDataBase.insertFavMeal(meal);
    }

    @Override
    public void removeMealFromPlan(Meal meal) {
        mealsLocalDataBase.deletePlannedMeal(meal);
    }
}
