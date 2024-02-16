package com.example.mealapp.home_screen.view;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

public interface HomeView {
    public void ShowFilterWithCategories(List<Category> categoryList);

    public void ShowFilterWithCountries(List<Country> countries);

    public void ShowFilterWithIngredients(List<Ingredient> ingredients);

    public void ShowFilteredMeals(List<Meal> meals);

}
