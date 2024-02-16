package com.example.mealapp.home_screen.model;

import com.example.mealapp.home_screen.network.IHomeNetworkCallBacks;
import com.example.mealapp.home_screen.network.IHomeRemoteDataSource;
import com.example.mealapp.network.NetworkCallBacks;

public class HomeScreenRepositoryImpl implements IHomeScreenRepository {
    private IHomeRemoteDataSource homeRemoteDataSource;
    private static HomeScreenRepositoryImpl instance = null;

    public static HomeScreenRepositoryImpl getInstance(IHomeRemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new HomeScreenRepositoryImpl(remoteDataSource);
        }
        return instance;
    }

    private HomeScreenRepositoryImpl(IHomeRemoteDataSource remoteDataSource) {
        this.homeRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getCategories(IHomeNetworkCallBacks networkCallBacks) {
        homeRemoteDataSource.getCategories(networkCallBacks);
    }

    @Override
    public void getCountries(IHomeNetworkCallBacks networkCallBacks) {

    }

    @Override
    public void getIngredients(IHomeNetworkCallBacks networkCallBacks) {

    }

    @Override
    public void getMealsFilteredByCategory(IHomeNetworkCallBacks networkCallBacks, Category category) {

    }

    @Override
    public void getMealsFilteredByCountry(IHomeNetworkCallBacks networkCallBacks, Country country) {

    }

    @Override
    public void getMealsFilteredByIngredient(IHomeNetworkCallBacks networkCallBacks, Ingredient ingredient) {

    }
}
