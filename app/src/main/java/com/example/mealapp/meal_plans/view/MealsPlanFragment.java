package com.example.mealapp.meal_plans.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealapp.R;
import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.db.MealsLocalDataBaseImpl;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.view.MealDetailsActivity;
import com.example.mealapp.meal_plans.model.IMealPlansRepository;
import com.example.mealapp.meal_plans.model.MealPlansRepositoryImpl;
import com.example.mealapp.meal_plans.network.IMealPlansRemoteDataSource;
import com.example.mealapp.meal_plans.network.MealPlansRemoteDataSourceImpl;
import com.example.mealapp.meal_plans.presenter.IMealPlansPresenter;
import com.example.mealapp.meal_plans.presenter.MealPlansPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsPlanFragment extends Fragment implements MealPlansView, OnPlansMealClickListener {

    private IMealPlansPresenter mealPlansPresenter;
    private TextView randomMealName;
    private ImageView randomMealImg;
    private boolean isLoaded = false;
    private Meal meal;
    private ProgressBar progressBar;
    private MealPlansAdapter saturdayPlansAdapter;
    private RecyclerView saturdayRecyclerView;
    private MealPlansAdapter sundayPlansAdapter;
    private RecyclerView sundRecyclerView;
    private MealPlansAdapter mondayPlansAdapter;
    private RecyclerView mondayRecyclerView;

    private MealPlansAdapter tuesdayPlansAdapter;
    private RecyclerView tuesdayRecyclerView;

    private MealPlansAdapter wednesdayPlansAdapter;
    private RecyclerView wednesdayRecyclerView;

    private MealPlansAdapter thursdayPlansAdapter;
    private RecyclerView thursdayRecyclerView;

    private MealPlansAdapter fridayPlansAdapter;
    private RecyclerView fridayRecyclerView;


    public MealsPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meals_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IMealsLocalDataBase mealsLocalDataBase = MealsLocalDataBaseImpl.getInstance(getActivity().getApplicationContext());
        IMealPlansRemoteDataSource mealPlansRemoteDataSource = MealPlansRemoteDataSourceImpl.getInstance();
        IMealPlansRepository mealPlansRepository = MealPlansRepositoryImpl.getInstance(mealPlansRemoteDataSource, mealsLocalDataBase);
        mealPlansPresenter = new MealPlansPresenterImpl(mealPlansRepository, this);
        LinearLayoutManager saturdayLinearLayoutManager = new LinearLayoutManager(view.getContext());
        saturdayLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager sundayLinearLayoutManager = new LinearLayoutManager(view.getContext());
        sundayLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager mondayLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mondayLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager tuesdayLinearLayoutManager = new LinearLayoutManager(view.getContext());
        tuesdayLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager wednesdayLinearLayoutManager = new LinearLayoutManager(view.getContext());
        wednesdayLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager thursdayLinearLayoutManager = new LinearLayoutManager(view.getContext());
        thursdayLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager fridayLinearLayoutManager = new LinearLayoutManager(view.getContext());
        fridayLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ImageView refresher = view.findViewById(R.id.refresherImg);
        saturdayPlansAdapter = new MealPlansAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        sundayPlansAdapter = new MealPlansAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        mondayPlansAdapter = new MealPlansAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        tuesdayPlansAdapter = new MealPlansAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        wednesdayPlansAdapter = new MealPlansAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        thursdayPlansAdapter = new MealPlansAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        fridayPlansAdapter = new MealPlansAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);

        saturdayRecyclerView = view.findViewById(R.id.saturdayRecyclerView);
        sundRecyclerView = view.findViewById(R.id.sundayRecyclerView);
        mondayRecyclerView = view.findViewById(R.id.mondayRecyclerView);
        tuesdayRecyclerView = view.findViewById(R.id.tuesRecyclerView);
        wednesdayRecyclerView = view.findViewById(R.id.wednesRecyclerView);
        thursdayRecyclerView = view.findViewById(R.id.thursRecyclerView);
        fridayRecyclerView = view.findViewById(R.id.friRecyclerView);

        saturdayRecyclerView.setLayoutManager(saturdayLinearLayoutManager);
        sundRecyclerView.setLayoutManager(sundayLinearLayoutManager);
        mondayRecyclerView.setLayoutManager(mondayLinearLayoutManager);
        tuesdayRecyclerView.setLayoutManager(tuesdayLinearLayoutManager);
        wednesdayRecyclerView.setLayoutManager(wednesdayLinearLayoutManager);
        thursdayRecyclerView.setLayoutManager(thursdayLinearLayoutManager);
        fridayRecyclerView.setLayoutManager(fridayLinearLayoutManager);

        saturdayRecyclerView.setAdapter(saturdayPlansAdapter);
        sundRecyclerView.setAdapter(sundayPlansAdapter);
        mondayRecyclerView.setAdapter(mondayPlansAdapter);
        tuesdayRecyclerView.setAdapter(tuesdayPlansAdapter);
        wednesdayRecyclerView.setAdapter(wednesdayPlansAdapter);
        thursdayRecyclerView.setAdapter(thursdayPlansAdapter);
        fridayRecyclerView.setAdapter(fridayPlansAdapter);

        randomMealImg = view.findViewById(R.id.randomMealImage);
        randomMealName = view.findViewById(R.id.randomMealName);
        Button addToFavBtn = view.findViewById(R.id.addToFavButtonMealPlans);
        CardView randomMealCardView = view.findViewById(R.id.randomMealCardView);
        progressBar = view.findViewById(R.id.mealPlansProgressBar);
        mealPlansPresenter.getRandomMeal();
        mealPlansPresenter.getSaturdayPlanMeals();
        mealPlansPresenter.getSundayPlanMeals();
        mealPlansPresenter.getMondayPlanMeals();
        mealPlansPresenter.getTuesdayPlanMeals();
        mealPlansPresenter.getWednesdayPlanMeals();
        mealPlansPresenter.getThursdayPlanMeals();
        mealPlansPresenter.getFridayPlanMeals();

        addToFavBtn.setOnClickListener(v -> {
            if (isLoaded) {
                mealPlansPresenter.addMealToFav(meal);
                Toast.makeText(getContext(), "Meal added to favourite", Toast.LENGTH_SHORT).show();

            }
        });
        randomMealCardView.setOnClickListener(v -> {
            if (isLoaded) {
                Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
                intent.putExtra("meal_id", meal.getId());
                startActivity(intent);
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
        if(this.getContext()!=null) {
            Glide.with(this).load(randomMeal.getImageLink()).override(120, 120).transform(new RoundedCorners(10)).centerCrop().into(randomMealImg);
        }

    }

    @Override
    public void showFailureMsg(String failureMsg) {
        Toast.makeText(getContext(), failureMsg, Toast.LENGTH_SHORT).show();
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
        saturdayMeal.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if (!item.isEmpty()) {
                        saturdayRecyclerView.setVisibility(View.VISIBLE);
                        saturdayPlansAdapter.updateData(item);
                    } else {
                        saturdayRecyclerView.setVisibility(View.GONE);
                        saturdayPlansAdapter.updateData(item);
                    }
                }
        );
    }

    @Override
    public void showSundayPlanMeals(Flowable<List<Meal>> sundayMeals) {
        sundayMeals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if (!item.isEmpty()) {
                        sundRecyclerView.setVisibility(View.VISIBLE);
                        sundayPlansAdapter.updateData(item);
                    } else {
                        sundRecyclerView.setVisibility(View.GONE);
                        sundayPlansAdapter.updateData(item);
                    }

                }

        );

    }

    @Override
    public void showMondayPlanMeals(Flowable<List<Meal>> mondayMeals) {
        mondayMeals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if (!item.isEmpty()) {
                        mondayRecyclerView.setVisibility(View.VISIBLE);
                        mondayPlansAdapter.updateData(item);
                    } else {
                        mondayRecyclerView.setVisibility(View.GONE);
                        mondayPlansAdapter.updateData(item);
                    }

                }

        );
    }

    @Override
    public void showTuesdayPlanMeals(Flowable<List<Meal>> tuesdayMeals) {
        tuesdayMeals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if (!item.isEmpty()) {
                        tuesdayRecyclerView.setVisibility(View.VISIBLE);
                        tuesdayPlansAdapter.updateData(item);
                    } else {
                        tuesdayRecyclerView.setVisibility(View.GONE);
                        tuesdayPlansAdapter.updateData(item);
                    }

                }

        );
    }

    @Override
    public void showWednesdayPlanMeals(Flowable<List<Meal>> wednesdayMeals) {
        wednesdayMeals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if (!item.isEmpty()) {
                        wednesdayRecyclerView.setVisibility(View.VISIBLE);
                        wednesdayPlansAdapter.updateData(item);
                    } else {
                        wednesdayRecyclerView.setVisibility(View.GONE);
                        wednesdayPlansAdapter.updateData(item);
                    }

                }

        );
    }

    @Override
    public void showThursdayPlanMeals(Flowable<List<Meal>> thursdayMeals) {
        thursdayMeals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if (!item.isEmpty()) {
                        thursdayRecyclerView.setVisibility(View.VISIBLE);
                        thursdayPlansAdapter.updateData(item);
                    } else {
                        thursdayRecyclerView.setVisibility(View.GONE);
                        thursdayPlansAdapter.updateData(item);
                    }

                }

        );
    }

    @Override
    public void showFridayPlanMeals(Flowable<List<Meal>> fridayMeals) {
        fridayMeals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if (!item.isEmpty()) {
                        fridayRecyclerView.setVisibility(View.VISIBLE);
                        fridayPlansAdapter.updateData(item);
                    } else {
                        fridayRecyclerView.setVisibility(View.GONE);
                        fridayPlansAdapter.updateData(item);
                    }

                }

        );
    }

    @Override
    public void showMealDetails(Meal meal) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("meal_id", meal.getId());
        startActivity(intent);
    }

    @Override
    public void onRemoveMealButtonClicked(Meal meal) {
        Log.i("removed ITem", "onRemoveMealButtonClicked: " + meal.getMealDay());
        Toast.makeText(getContext(), "Meal removed from " + meal.getMealDay() + " plan", Toast.LENGTH_SHORT).show();
        mealPlansPresenter.removeMealFromPlan(meal);
    }
}