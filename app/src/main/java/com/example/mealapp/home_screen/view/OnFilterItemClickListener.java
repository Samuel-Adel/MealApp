package com.example.mealapp.home_screen.view;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;

public interface OnFilterItemClickListener {
    public void changeFilterSelectedItemCountry(Country country);
    public void changeFilterSelectedItemCategory(Category category);
    public void changeFilterSelectedItemIngredient(Ingredient ingredient);
    public void addMealToFav(Meal meal);
    public void showMealDetails(Meal meal);


}
