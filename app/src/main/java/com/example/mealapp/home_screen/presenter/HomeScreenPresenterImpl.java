package com.example.mealapp.home_screen.presenter;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.IHomeScreenRepository;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.home_screen.network.IHomeNetworkCallBacks;
import com.example.mealapp.home_screen.view.HomeView;

import java.util.List;

public class HomeScreenPresenterImpl implements IHomeScreenPresenter, IHomeNetworkCallBacks {
    private IHomeScreenRepository homeScreenRepository;
    private HomeView homeView;

    public HomeScreenPresenterImpl(IHomeScreenRepository homeScreenRepository, HomeView homeView) {
        this.homeScreenRepository = homeScreenRepository;
        this.homeView = homeView;
    }

    @Override
    public void getMealsByCategory(Category category) {
        homeScreenRepository.getMealsFilteredByCategory(this, category);
    }

    @Override
    public void getMealsByCountry(Country country) {
        homeScreenRepository.getMealsFilteredByCountry(this, country);
    }

    @Override
    public void getMealsByIngredient(Ingredient ingredient) {
        homeScreenRepository.getMealsFilteredByIngredient(this, ingredient);
    }

    @Override
    public void getCategories() {
        homeScreenRepository.getCategories(this);
    }

    @Override
    public void getCountries() {
        homeScreenRepository.getCountries(this);
    }

    @Override
    public void getIngredients() {
        homeScreenRepository.getIngredients(this);
    }

    @Override
    public void addMealToFavourites(Meal meal) {
        homeScreenRepository.addMealToFavourite(meal);
    }

    @Override
    public void logOut() {
        homeScreenRepository.logOut();
    }

    @Override
    public void onCategoriesSuccessfulCallBack(List<Category> categories) {
        homeView.showFilterWithCategories(categories);
    }

    @Override
    public void onCountriesSuccessfulCallBack(List<Country> countries) {
        homeView.showFilterWithCountries(countries);
    }

    @Override
    public void onIngredientsSuccessfulCallBack(List<Ingredient> ingredients) {
        homeView.showFilterWithIngredients(ingredients);
    }

    @Override
    public void onMealsSuccessfulCallBack(List<Meal> meals) {
        homeView.showFilteredMeals(meals);
    }

    @Override
    public void onFailureResult(String failureMsg) {
        homeView.showErrorMessage(failureMsg);

    }

    @Override
    public void callInProgressStatus(boolean status) {
        homeView.statusBarVisibilityStatus(status);
    }
}
