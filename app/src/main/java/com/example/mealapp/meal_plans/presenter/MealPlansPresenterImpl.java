package com.example.mealapp.meal_plans.presenter;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_plans.model.Days;
import com.example.mealapp.meal_plans.model.IMealPlansRepository;
import com.example.mealapp.meal_plans.network.IMealPlansNetworkCallBacks;
import com.example.mealapp.meal_plans.view.MealPlansView;

public class MealPlansPresenterImpl implements IMealPlansPresenter, IMealPlansNetworkCallBacks {
    private IMealPlansRepository mealPlansRepository;
    private MealPlansView mealPlansView;

    public MealPlansPresenterImpl(IMealPlansRepository mealPlansRepository, MealPlansView mealPlansView) {
        this.mealPlansRepository = mealPlansRepository;
        this.mealPlansView = mealPlansView;
    }

    @Override
    public void onSuccessfulCallBack(Meal meal) {
        mealPlansView.showRandomMeal(meal);
    }

    @Override
    public void onFailureResult(String failureMsg) {
        mealPlansView.showFailureMsg(failureMsg);
    }

    @Override
    public void callInProgressStatus(boolean status) {
        mealPlansView.showProgressBar(status);
    }

    @Override
    public void getRandomMeal() {
        mealPlansRepository.getRandomMeal(this);
    }

    @Override
    public void getSaturdayPlanMeals() {
        mealPlansView.showSaturdayPlanMeals(mealPlansRepository.getSaturdayPlanMeals());
    }

    @Override
    public void getSundayPlanMeals() {
        mealPlansView.showSundayPlanMeals(mealPlansRepository.getSundayPlanMeals());
    }

    @Override
    public void getMondayPlanMeals() {
        mealPlansView.showMondayPlanMeals(mealPlansRepository.getMondayPlanMeals());
    }

    @Override
    public void getTuesdayPlanMeals() {
        mealPlansView.showTuesdayPlanMeals(mealPlansRepository.getTuesdayPlanMeals());
    }

    @Override
    public void getWednesdayPlanMeals() {
        mealPlansView.showWednesdayPlanMeals(mealPlansRepository.getWednesdayPlanMeals());
    }

    @Override
    public void getThursdayPlanMeals() {
        mealPlansView.showThursdayPlanMeals(mealPlansRepository.getThursdayPlanMeals());
    }

    @Override
    public void getFridayPlanMeals() {
        mealPlansView.showFridayPlanMeals(mealPlansRepository.getFridayPlanMeals());
    }

    @Override
    public void addMealToFav(Meal meal) {
        mealPlansRepository.addMealToFav(meal);
    }

    @Override
    public void addMealToPlan(Meal meal, Days day) {
        mealPlansRepository.addMealToPlan(meal, day);
    }
}
