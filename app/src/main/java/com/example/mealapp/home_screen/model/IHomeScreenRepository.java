package com.example.mealapp.home_screen.model;

import com.example.mealapp.home_screen.network.IHomeNetworkCallBacks;

public interface IHomeScreenRepository {
    public void getCategories(IHomeNetworkCallBacks networkCallBacks);

    public void getCountries(IHomeNetworkCallBacks networkCallBacks);

    public void getIngredients(IHomeNetworkCallBacks networkCallBacks);

    public void getMealsFilteredByCategory(IHomeNetworkCallBacks networkCallBacks, Category category);

    public void getMealsFilteredByCountry(IHomeNetworkCallBacks networkCallBacks, Country country);

    public void getMealsFilteredByIngredient(IHomeNetworkCallBacks networkCallBacks, Ingredient ingredient);
    public void addMealToFavourite(IHomeNetworkCallBacks networkCallBacks, Meal meal);


}
