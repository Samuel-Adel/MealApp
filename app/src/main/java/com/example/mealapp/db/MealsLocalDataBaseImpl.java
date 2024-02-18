package com.example.mealapp.db;

import android.content.Context;

import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataBaseImpl implements IMealsLocalDataBase {
    private Flowable<List<Meal>> meals;
    private MealsDAO mealsDAO;
    private static MealsLocalDataBaseImpl instance = null;

    public static MealsLocalDataBaseImpl getInstance(Context context) {
        if (instance == null) {
            instance = new MealsLocalDataBaseImpl(context);
        }
        return instance;
    }

    private MealsLocalDataBaseImpl(Context context) {
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

    @Override
    public Flowable<List<Meal>> getFavMeals() {
        return mealsDAO.getFavoriteMeals();
    }

    @Override
    public Flowable<Boolean> isFavMeal(String mealId) {
        return mealsDAO.isMealFavorite(mealId);
    }

    @Override
    public Flowable<List<Meal>> getSaturdayMeals() {
        return mealsDAO.getSaturdayMeals();
    }

    @Override
    public Flowable<List<Meal>> getSundayMeals() {
        return mealsDAO.getSundayMeals();
    }

    @Override
    public Flowable<List<Meal>> getMondayMeals() {
        return mealsDAO.getMondayMeals();
    }

    @Override
    public Flowable<List<Meal>> getTuesdayMeals() {
        return mealsDAO.getTuesdayMeals();
    }

    @Override
    public Flowable<List<Meal>> getWednesdayMeals() {
        return mealsDAO.getWednesdayMeals();
    }

    @Override
    public Flowable<List<Meal>> getThursdayMeals() {
        return mealsDAO.getThursdayMeals();
    }

    @Override
    public Flowable<List<Meal>> getFridayMeals() {
        return mealsDAO.getFridayMeals();
    }
}
