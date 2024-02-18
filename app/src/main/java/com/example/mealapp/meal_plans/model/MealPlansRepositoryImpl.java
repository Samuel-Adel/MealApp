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
        return null;
    }

    @Override
    public Flowable<List<Meal>> getSundayPlanMeals() {
        return null;
    }

    @Override
    public Flowable<List<Meal>> getMondayPlanMeals() {
        return null;
    }

    @Override
    public Flowable<List<Meal>> getTuesdayPlanMeals() {
        return null;
    }

    @Override
    public Flowable<List<Meal>> getWednesdayPlanMeals() {
        return null;
    }

    @Override
    public Flowable<List<Meal>> getThursdayPlanMeals() {
        return null;
    }

    @Override
    public Flowable<List<Meal>> getFridayPlanMeals() {
        return null;
    }

    @Override
    public void addMealToFav(Meal meal) {
        mealsLocalDataBase.insertMeal(meal);
    }

    @Override
    public void addMealToPlan(Meal meal, Days day) {

    }
}
