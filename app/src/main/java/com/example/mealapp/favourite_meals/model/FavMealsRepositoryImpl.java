package com.example.mealapp.favourite_meals.model;

import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class FavMealsRepositoryImpl implements IFavMealsRepository {
    private final IMealsLocalDataBase favMealsLocalDataSource;
    private static FavMealsRepositoryImpl instance = null;

    public static FavMealsRepositoryImpl getInstance(IMealsLocalDataBase localDataSource) {
        if (instance == null) {
            instance = new FavMealsRepositoryImpl(localDataSource);
        }
        return instance;
    }

    private FavMealsRepositoryImpl(IMealsLocalDataBase localDataSource) {
        this.favMealsLocalDataSource = localDataSource;
    }


    @Override
    public void deleteMeal(Meal meal) {
        favMealsLocalDataSource.deleteFavMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getStoredMeals() {
        return favMealsLocalDataSource.getFavMeals();
    }

    @Override
    public Flowable<Boolean> checkIfFavOrNot(Meal meal) {
        return favMealsLocalDataSource.isFavMeal(meal.getId());
    }
}
