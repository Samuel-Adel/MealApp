package com.example.mealapp.favourite_meals.presenter;

import com.example.mealapp.favourite_meals.model.IFavMealsRepository;
import com.example.mealapp.favourite_meals.view.FavScreenView;
import com.example.mealapp.home_screen.model.Meal;

import io.reactivex.rxjava3.core.Flowable;

public class FavScreenPresenterImpl implements IFavScreenPresenter {
    private IFavMealsRepository favMealsRepository;
    private FavScreenView favScreenView;

    public FavScreenPresenterImpl(IFavMealsRepository favMealsRepository, FavScreenView favScreenView) {
        this.favMealsRepository = favMealsRepository;
        this.favScreenView = favScreenView;
    }

    @Override
    public void removeMealFromFavourite(Meal meal) {
        favMealsRepository.deleteMeal(meal);
    }

    @Override
    public void getAllFavouriteMeals() {
        favScreenView.showStoredMeals(favMealsRepository.getStoredMeals());
    }

    @Override
    public Flowable<Boolean> checkIfFavOrNot(Meal meal) {
        return favMealsRepository.checkIfFavOrNot(meal);
    }
}
