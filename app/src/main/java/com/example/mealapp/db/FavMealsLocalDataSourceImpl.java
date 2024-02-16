package com.example.mealapp.db;

import android.content.Context;

import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class FavMealsLocalDataSourceImpl implements IFavMealsLocalDataSource {
    private Flowable<List<Meal>> meals;
    private MealsDAO mealsDAO;
    private static FavMealsLocalDataSourceImpl instance = null;

    public static FavMealsLocalDataSourceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new FavMealsLocalDataSourceImpl(context);
        }
        return instance;
    }

    private FavMealsLocalDataSourceImpl(Context context) {
        mealsDAO = AppDataBase.getInstance(context.getApplicationContext()).geProductsDAO();
        meals = mealsDAO.getAllProducts();
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(() -> {
            mealsDAO.insertProduct(meal);
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(() -> {
            mealsDAO.deleteProduct(meal);
        }).start();
    }

    @Override
    public Flowable<List<Meal>> getAllMeals() {
        return meals;
    }
}
