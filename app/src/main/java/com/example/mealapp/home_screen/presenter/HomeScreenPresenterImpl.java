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
    public void getMealsByCategory() {

    }

    @Override
    public void getMealsByCountry() {

    }

    @Override
    public void getMealsByIngredient() {

    }

    @Override
    public void getCategories() {
        homeScreenRepository.getCategories(this);
    }

    @Override
    public void getCountries() {

    }

    @Override
    public void getIngredients() {

    }

    @Override
    public void onCategoriesSuccessfulCallBack(List<Category> categories) {
        homeView.ShowFilterWithCategories(categories);
    }

    @Override
    public void onCountriesSuccessfulCallBack(List<Country> countries) {

    }

    @Override
    public void onIngredientsSuccessfulCallBack(List<Ingredient> ingredients) {

    }

    @Override
    public void onMealsSuccessfulCallBack(List<Meal> meals) {

    }

    @Override
    public void onFailureResult(String failureMsg) {

    }

    @Override
    public void callInProgressStatus(boolean status) {

    }
}
