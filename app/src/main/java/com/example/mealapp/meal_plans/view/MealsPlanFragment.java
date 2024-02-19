package com.example.mealapp.meal_plans.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealapp.R;
import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.db.MealsLocalDataBaseImpl;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_plans.model.IMealPlansRepository;
import com.example.mealapp.meal_plans.model.MealPlansRepositoryImpl;
import com.example.mealapp.meal_plans.network.IMealPlansRemoteDataSource;
import com.example.mealapp.meal_plans.network.MealPlansRemoteDataSourceImpl;
import com.example.mealapp.meal_plans.presenter.IMealPlansPresenter;
import com.example.mealapp.meal_plans.presenter.MealPlansPresenterImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public class MealsPlanFragment extends Fragment implements MealPlansView {

    private IMealPlansPresenter mealPlansPresenter;
    private TextView randomMealName;
    private Button addToFavBtn;
    private ImageView randomMealImg;
    private boolean isLoaded = false;
    private Meal meal;
    private ProgressBar progressBar;

    public MealsPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meals_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IMealsLocalDataBase mealsLocalDataBase = MealsLocalDataBaseImpl.getInstance(getActivity().getApplicationContext());
        IMealPlansRemoteDataSource mealPlansRemoteDataSource = MealPlansRemoteDataSourceImpl.getInstance();
        IMealPlansRepository mealPlansRepository = MealPlansRepositoryImpl.getInstance(mealPlansRemoteDataSource, mealsLocalDataBase);
        mealPlansPresenter = new MealPlansPresenterImpl(mealPlansRepository, this);
        randomMealImg = view.findViewById(R.id.randomMealImage);
        randomMealName = view.findViewById(R.id.randomMealName);
        addToFavBtn = view.findViewById(R.id.addToFavButtonMealPlans);
        ImageView refresher = view.findViewById(R.id.refresherImg);
        progressBar = view.findViewById(R.id.mealPlansProgressBar);
        mealPlansPresenter.getRandomMeal();
        addToFavBtn.setOnClickListener(v -> {
            if (isLoaded) {
                mealPlansPresenter.addMealToFav(meal);
            }
        });

        refresher.setOnClickListener(v -> {
            if (isLoaded) {
                mealPlansPresenter.getRandomMeal();
                isLoaded = false;
            }

        });


    }

    @Override
    public void showRandomMeal(Meal randomMeal) {
        this.meal = randomMeal;
        isLoaded = true;
        randomMealName.setText(randomMeal.getName());
        Glide.with(this).load(randomMeal.getImageLink()).override(120, 120).
                transform(new RoundedCorners(10)).centerCrop().into(randomMealImg);

    }

    @Override
    public void showFailureMsg(String failureMsg) {

    }

    @Override
    public void showProgressBar(boolean status) {
        if (status) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSaturdayPlanMeals(Flowable<List<Meal>> saturdayMeal) {

    }

    @Override
    public void showSundayPlanMeals(Flowable<List<Meal>> sundayMeals) {

    }

    @Override
    public void showMondayPlanMeals(Flowable<List<Meal>> mondayMeals) {

    }

    @Override
    public void showTuesdayPlanMeals(Flowable<List<Meal>> tuesdayMeals) {

    }

    @Override
    public void showWednesdayPlanMeals(Flowable<List<Meal>> wednesdayMeals) {

    }

    @Override
    public void showThursdayPlanMeals(Flowable<List<Meal>> thursdayMeals) {

    }

    @Override
    public void showFridayPlanMeals(Flowable<List<Meal>> fridayMeals) {

    }
}