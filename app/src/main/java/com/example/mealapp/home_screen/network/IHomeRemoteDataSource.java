package com.example.mealapp.home_screen.network;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;
public interface IHomeRemoteDataSource {
    void getMealsByCategory(IHomeNetworkCallBacks networkCallBacks, Category category);

    void getMealsByCountry(IHomeNetworkCallBacks networkCallBacks, Country country);

    void getMealsByIngredient(IHomeNetworkCallBacks networkCallBacks, Ingredient ingredient);

    void getCategories(IHomeNetworkCallBacks networkCallBacks);

    void getCountries(IHomeNetworkCallBacks networkCallBacks);

    void getIngredients(IHomeNetworkCallBacks networkCallBacks);

}
