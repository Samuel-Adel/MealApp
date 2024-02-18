package com.example.mealapp.favourite_meals.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealapp.R;
import com.example.mealapp.db.MealsLocalDataBaseImpl;
import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.favourite_meals.model.FavMealsRepositoryImpl;
import com.example.mealapp.favourite_meals.model.IFavMealsRepository;
import com.example.mealapp.favourite_meals.presenter.FavScreenPresenterImpl;
import com.example.mealapp.favourite_meals.presenter.IFavScreenPresenter;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.view.MealDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavMealsScreenFragment extends Fragment implements FavScreenView, onFavScreenClickListener {

    private Flowable<List<Meal>> favMeals;

    private IFavScreenPresenter favScreenPresenter;
    private FavMealsAdapter favMealsAdapter;

    public FavMealsScreenFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_meals_screen, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IMealsLocalDataBase favMealsLocalDataSource = MealsLocalDataBaseImpl.getInstance(getActivity().getApplicationContext());
        IFavMealsRepository favMealsRepository = FavMealsRepositoryImpl.getInstance(favMealsLocalDataSource);
        favScreenPresenter = new FavScreenPresenterImpl(favMealsRepository, this);
        favMealsAdapter = new FavMealsAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        RecyclerView recyclerViewFavMeals = view.findViewById(R.id.recyclerFavMeals);
        recyclerViewFavMeals.setNestedScrollingEnabled(false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerViewFavMeals.setLayoutManager(gridLayoutManager);
        recyclerViewFavMeals.setAdapter(favMealsAdapter);
        favScreenPresenter.getAllFavouriteMeals();
    }

    @Override
    public void showStoredMeals(Flowable<List<Meal>> meals) {
        favMeals = meals;
        favMeals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    favMealsAdapter.updateData(item);
                }

        );
    }

    @Override
    public void removeFromFavItems(Meal meal) {
        favScreenPresenter.removeMealFromFavourite(meal);
        Toast.makeText(getContext(), "Removed from favourite", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMealDetails(Meal meal) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("meal_id", meal.getId());
        startActivity(intent);
    }
}