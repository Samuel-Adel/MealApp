package com.example.mealapp.meal_details.presenter;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.model.IMealDetailsRepository;
import com.example.mealapp.meal_details.network.IMealDetailsNetworkCallBack;
import com.example.mealapp.meal_details.view.MealDetailsView;
import com.example.mealapp.meal_plans.model.Days;

public class MealDetailsPresenterImpl implements IMealDetailsPresenter, IMealDetailsNetworkCallBack {
    private final IMealDetailsRepository mealDetailsRepository;
    private final MealDetailsView mealDetailsView;

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
    public void removeMealFromFav(Meal meal) {
        mealDetailsRepository.removeMealFromFavourite(meal);
    }

    @Override
    public void addMealToPlan(Meal meal, Days day) {
        mealDetailsRepository.addMealToPlan(meal, day);
    }

    @Override
    public void removeMealFromPlan(Meal meal) {
        mealDetailsRepository.removeMealFromPlan(meal);
    }

    @Override
    public void checkIfMealIsFav(Meal meal) {
        mealDetailsView.showIfMealISFav(mealDetailsRepository.isMealFav(meal));
    }



    @Override
    public void checkIfMealIsPlanned(Meal meal) {
        mealDetailsView.showIfMealIsPlanned(mealDetailsRepository.isMealPlanned(meal));
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
