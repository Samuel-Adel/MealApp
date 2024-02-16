package com.example.mealapp.favourite_meals.model;

import com.example.mealapp.db.IFavMealsLocalDataSource;
import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class FavMealsRepositoryImpl implements IFavMealsRepository {
    private IFavMealsLocalDataSource favMealsLocalDataSource;
    private static FavMealsRepositoryImpl instance = null;

    public static FavMealsRepositoryImpl getInstance(IFavMealsLocalDataSource localDataSource) {
        if (instance == null) {
            instance = new FavMealsRepositoryImpl(localDataSource);
        }
        return instance;
    }

    private FavMealsRepositoryImpl(IFavMealsLocalDataSource localDataSource) {
        this.favMealsLocalDataSource = localDataSource;
    }



    @Override
    public void deleteMeal(Meal meal) {
        favMealsLocalDataSource.deleteMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getStoredMeals() {
        return favMealsLocalDataSource.getAllMeals();
    }
}
