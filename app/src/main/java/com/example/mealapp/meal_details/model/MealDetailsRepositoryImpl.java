package com.example.mealapp.meal_details.model;

import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.network.IMealDetailsNetworkCallBack;
import com.example.mealapp.meal_details.network.IMealDetailsRemoteDataSource;
import com.example.mealapp.meal_plans.model.Days;

import io.reactivex.rxjava3.core.Flowable;

public class MealDetailsRepositoryImpl implements IMealDetailsRepository {
    private final IMealDetailsRemoteDataSource mealDetailsRemoteDataSource;
    private final IMealsLocalDataBase mealsLocalDataBase;
    private static MealDetailsRepositoryImpl instance = null;

    public static MealDetailsRepositoryImpl getInstance(IMealDetailsRemoteDataSource remoteDataSource, IMealsLocalDataBase favMealsLocalDataSource) {
        if (instance == null) {
            instance = new MealDetailsRepositoryImpl(remoteDataSource, favMealsLocalDataSource);
        }
        return instance;
    }

    private MealDetailsRepositoryImpl(IMealDetailsRemoteDataSource remoteDataSource, IMealsLocalDataBase mealsLocalDataBase) {
        this.mealDetailsRemoteDataSource = remoteDataSource;
        this.mealsLocalDataBase = mealsLocalDataBase;
    }

    @Override
    public void getMealDetails(IMealDetailsNetworkCallBack mealDetailsNetworkCallBack, String mealId) {
        mealDetailsRemoteDataSource.getMealDetails(mealDetailsNetworkCallBack, mealId);
    }

    @Override
    public void addMealToFavourite(Meal meal) {
        mealsLocalDataBase.insertFavMeal(meal);
    }

    @Override
    public void removeMealFromFavourite(Meal meal) {
        mealsLocalDataBase.deleteFavMeal(meal);
    }

    @Override
    public void addMealToPlan(Meal meal, Days day) {
        meal.setMealDay(day.name());
        mealsLocalDataBase.insertMealPlan(meal, day);
    }

    @Override
    public void removeMealFromPlan(Meal meal) {
        mealsLocalDataBase.deletePlannedMeal(meal);
    }


    @Override
    public Flowable<Boolean> isMealFav(Meal meal) {
        return mealsLocalDataBase.isFavMeal(meal.getId());
    }

    @Override
    public Flowable<Boolean> isMealPlanned(Meal meal) {
        return mealsLocalDataBase.isFavMeal(meal.getId());

    }
}
