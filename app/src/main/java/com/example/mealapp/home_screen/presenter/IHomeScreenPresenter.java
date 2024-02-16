package com.example.mealapp.home_screen.presenter;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;

public interface IHomeScreenPresenter {
    public void getMealsByCategory(Category category);
    public void getMealsByCountry(Country country);
    public void getMealsByIngredient(Ingredient ingredient);
    public void getCategories();
    public void getCountries();
    public void getIngredients();
    public void addMealToFavourites(Meal meal);
}
