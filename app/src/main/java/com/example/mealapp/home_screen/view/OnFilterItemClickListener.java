package com.example.mealapp.home_screen.view;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;

public interface OnFilterItemClickListener {
    public void changeFilterSelectedItemCountry(Country country);
    public void changeFilterSelectedItemCategory(Category category);
    public void changeFilterSelectedItemIngredient(Ingredient ingredient);


}
