package com.example.mealapp.meal_details.presenter;

import com.example.mealapp.home_screen.model.IHomeScreenRepository;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.home_screen.view.HomeView;
import com.example.mealapp.meal_details.model.IMealDetailsRepository;
import com.example.mealapp.meal_details.network.IMealDetailsNetworkCallBack;
import com.example.mealapp.meal_details.view.MealDetailsView;

public class MealDetailsPresenterImpl implements IMealDetailsPresenter, IMealDetailsNetworkCallBack {
    private IMealDetailsRepository mealDetailsRepository;
    private MealDetailsView mealDetailsView;

    public MealDetailsPresenterImpl(IMealDetailsRepository mealDetailsRepository, MealDetailsView mealDetailsView) {
        this.mealDetailsRepository = mealDetailsRepository;
        this.mealDetailsView = mealDetailsView;
    }

    @Override
    public void getMealDetails(String mealId) {
        mealDetailsRepository.getMealDetails(this, mealId);
    }

    @Override
    public void addMealToFav(Meal meal) {
        mealDetailsRepository.addMealToFavourite(meal);
    }

    @Override
    public void addMealToPlan(Meal meal) {

    }

    @Override
    public void onSuccessfulResult(Meal meal) {
        mealDetailsView.showMealDetail(meal);
    }

    @Override
    public void onFailureResult(String failureMsg) {
        mealDetailsView.showErrorMessage(failureMsg);
    }

    @Override
    public void callInProgressStatus(boolean status) {
        mealDetailsView.showOrHideProgressBar(status);
    }
}
