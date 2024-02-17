package com.example.mealapp.meal_details.model;

import com.example.mealapp.db.IFavMealsLocalDataSource;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.network.IMealDetailsNetworkCallBack;
import com.example.mealapp.meal_details.network.IMealDetailsRemoteDataSource;

public class MealDetailsRepositoryImpl implements IMealDetailsRepository {
    private IMealDetailsRemoteDataSource mealDetailsRemoteDataSource;
    private IFavMealsLocalDataSource favMealsLocalDataSource;
    private static MealDetailsRepositoryImpl instance = null;

    public static MealDetailsRepositoryImpl getInstance(IMealDetailsRemoteDataSource remoteDataSource, IFavMealsLocalDataSource favMealsLocalDataSource) {
        if (instance == null) {
            instance = new MealDetailsRepositoryImpl(remoteDataSource, favMealsLocalDataSource);
        }
        return instance;
    }

    private MealDetailsRepositoryImpl(IMealDetailsRemoteDataSource remoteDataSource, IFavMealsLocalDataSource favMealsLocalDataSource) {
        this.mealDetailsRemoteDataSource = remoteDataSource;
        this.favMealsLocalDataSource = favMealsLocalDataSource;
    }

    @Override
    public void getMealDetails(IMealDetailsNetworkCallBack mealDetailsNetworkCallBack, String mealId) {
        mealDetailsRemoteDataSource.getMealDetails(mealDetailsNetworkCallBack, mealId);
    }

    @Override
    public void addMealToFavourite(Meal meal) {
        favMealsLocalDataSource.insertMeal(meal);
    }
}
