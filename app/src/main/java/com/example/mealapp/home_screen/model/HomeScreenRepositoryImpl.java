package com.example.mealapp.home_screen.model;

import com.example.mealapp.db.IFavMealsLocalDataSource;
import com.example.mealapp.home_screen.network.IHomeNetworkCallBacks;
import com.example.mealapp.home_screen.network.IHomeRemoteDataSource;
import com.example.mealapp.network.NetworkCallBacks;

public class HomeScreenRepositoryImpl implements IHomeScreenRepository {
    private IHomeRemoteDataSource homeRemoteDataSource;
    private IFavMealsLocalDataSource favMealsLocalDataSource;
    private static HomeScreenRepositoryImpl instance = null;

    public static HomeScreenRepositoryImpl getInstance(IHomeRemoteDataSource remoteDataSource,IFavMealsLocalDataSource favMealsLocalDataSource) {
        if (instance == null) {
            instance = new HomeScreenRepositoryImpl(remoteDataSource,favMealsLocalDataSource);
        }
        return instance;
    }

    private HomeScreenRepositoryImpl(IHomeRemoteDataSource remoteDataSource,IFavMealsLocalDataSource favMealsLocalDataSource) {
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
    public void addMealToFavourite(IHomeNetworkCallBacks networkCallBacks, Meal meal) {
        favMealsLocalDataSource.insertMeal(meal);
    }
}
