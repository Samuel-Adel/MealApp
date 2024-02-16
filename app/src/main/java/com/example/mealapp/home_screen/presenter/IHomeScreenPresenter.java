package com.example.mealapp.home_screen.presenter;

public interface IHomeScreenPresenter {
    public void getMealsByCategory();
    public void getMealsByCountry();
    public void getMealsByIngredient();
    public void getCategories();
    public void getCountries();
    public void getIngredients();
}
