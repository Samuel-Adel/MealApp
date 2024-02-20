package com.example.mealapp.home_screen.view;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

public interface HomeView {
    public void showFilterWithCategories(List<Category> categoryList);

    public void showFilterWithCountries(List<Country> countries);

    public void showFilterWithIngredients(List<Ingredient> ingredients);

    public void showFilteredMeals(List<Meal> meals);
    public void showErrorMessage(String errorMessage);

    public void statusBarVisibilityStatus(boolean isVisible);

}
