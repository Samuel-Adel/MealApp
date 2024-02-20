package com.example.mealapp.home_screen.model;

import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.home_screen.network.IHomeNetworkCallBacks;
import com.example.mealapp.home_screen.network.IHomeRemoteDataSource;

public class HomeScreenRepositoryImpl implements IHomeScreenRepository {
    private final IHomeRemoteDataSource homeRemoteDataSource;
    private final IMealsLocalDataBase favMealsLocalDataSource;
    private static HomeScreenRepositoryImpl instance = null;

    public static HomeScreenRepositoryImpl getInstance(IHomeRemoteDataSource remoteDataSource, IMealsLocalDataBase favMealsLocalDataSource) {
        if (instance == null) {
            instance = new HomeScreenRepositoryImpl(remoteDataSource, favMealsLocalDataSource);
        }
        return instance;
    }

    private HomeScreenRepositoryImpl(IHomeRemoteDataSource remoteDataSource, IMealsLocalDataBase favMealsLocalDataSource) {
        this.homeRemoteDataSource = remoteDataSource;
        this.favMealsLocalDataSource = favMealsLocalDataSource;
    }

    @Override
    public void getCategories(IHomeNetworkCallBacks networkCallBacks) {
        homeRemoteDataSource.getCategories(networkCallBacks);
    }

    @Override
    public void getCountries(IHomeNetworkCallBacks networkCallBacks) {
        homeRemoteDataSource.getCountries(networkCallBacks);
    }

    @Override
    public void getIngredients(IHomeNetworkCallBacks networkCallBacks) {
        homeRemoteDataSource.getIngredients(networkCallBacks);
    }

    @Override
    public void getMealsFilteredByCategory(IHomeNetworkCallBacks networkCallBacks, Category category) {
        homeRemoteDataSource.getMealsByCategory(networkCallBacks, category);
    }

    @Override
    public void getMealsFilteredByCountry(IHomeNetworkCallBacks networkCallBacks, Country country) {
        homeRemoteDataSource.getMealsByCountry(networkCallBacks, country);
    }

    @Override
    public void getMealsFilteredByIngredient(IHomeNetworkCallBacks networkCallBacks, Ingredient ingredient) {
        homeRemoteDataSource.getMealsByIngredient(networkCallBacks, ingredient);
    }

    @Override
    public void addMealToFavourite( Meal meal) {
        favMealsLocalDataSource.insertFavMeal(meal);
    }

    @Override
    public void logOut() {
        favMealsLocalDataSource.logOut();
    }
}
