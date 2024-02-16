package com.example.mealapp.home_screen.network;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

public interface IHomeNetworkCallBacks {
    public void onCategoriesSuccessfulCallBack(List<Category> categories);
    public void onCountriesSuccessfulCallBack(List<Country> countries);
    public void onIngredientsSuccessfulCallBack(List<Ingredient> ingredients);
    public void onMealsSuccessfulCallBack(List<Meal> meals);

    public void onFailureResult(String failureMsg);
    public void callInProgressStatus(boolean status);
}
