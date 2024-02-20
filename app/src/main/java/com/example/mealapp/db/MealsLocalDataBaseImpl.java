package com.example.mealapp.db;

import android.content.Context;
import android.util.Log;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.user.UserSavedCredentialsManager;
import com.example.mealapp.meal_plans.model.Days;
import com.example.mealapp.user.backup.FireBaseUser;
import com.example.mealapp.user.backup.FirebaseBackUp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataBaseImpl implements IMealsLocalDataBase {

    private final MealsDAO mealsDAO;
    private static MealsLocalDataBaseImpl instance = null;
    private AppDataBase appDataBase;
    private UserSavedCredentialsManager userSavedCredentialsManager;

    public static MealsLocalDataBaseImpl getInstance(Context context) {
        if (instance == null) {
            instance = new MealsLocalDataBaseImpl(context);
        }
        return instance;
    }

    private MealsLocalDataBaseImpl(Context context) {
        appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealsDAO = appDataBase.getMealsDAO();
        userSavedCredentialsManager = UserSavedCredentialsManager.getInstance(context);
    }


    @Override
    public void insertFavMeal(Meal meal) {
        new Thread(() -> mealsDAO.insertFavMeal(new FavMealModel(meal.getId(), meal.getName(), meal.getImageLink()))).start();
    }

    @Override
    public void logOut() {
        List<FavMealModel> favMeals = mealsDAO.getAllFavMeals().blockingFirst();
        List<PlannedMealModel> plannedMeals = mealsDAO.getAllPlannedMeals().blockingFirst();
        FireBaseUser user = new FireBaseUser(userSavedCredentialsManager.getUserToken(), favMeals, plannedMeals);
        FirebaseBackUp.saveUserDataToFirebase(user);
        new Thread(() -> appDataBase.clearAllTables()).start();
        userSavedCredentialsManager.clearUserToken();
    }

    @Override
    public void insertMealPlan(Meal meal, Days day) {
        new Thread(() -> mealsDAO.insertPlannedMeal(new PlannedMealModel(meal.getName(), meal.getImageLink(), meal.getId(), day.name()))).start();
    }

    @Override
    public void insertMealPlan(Meal meal, String day) {
        new Thread(() -> mealsDAO.insertPlannedMeal(new PlannedMealModel(meal.getName(), meal.getImageLink(), meal.getId(), day))).start();

    }

    @Override
    public void deleteFavMeal(Meal meal) {
        new Thread(() -> mealsDAO.deleteFavMealById(meal.getId())).start();
    }

    @Override
    public void deletePlannedMeal(Meal meal) {
        new Thread(() -> mealsDAO.deletePlannedMealById(meal.getId(), meal.getMealDay())).start();

    }


    @Override
    public Flowable<List<Meal>> getFavMeals() {
        return mealsDAO.getAllFavMeals().map(favMeals -> {
            Log.i("GettingFavMeals", "getFavMeals: " + favMeals.toArray().length);

            List<Meal> meals = new ArrayList<>();
            for (FavMealModel favMeal : favMeals) {
                Meal meal = new Meal(favMeal.getId(), favMeal.getName(), favMeal.getImageLink(), true // Assuming all fav meals are marked as favorite
                );
                meals.add(meal);
            }
            Log.i("GettingFavMeals", "getFavMeals: ");
            return meals;
        });
    }

    @Override
    public Flowable<List<Meal>> getAllPlannedMeals() {
        return mealsDAO.getAllPlannedMeals().map(plannedMeals -> {
            Log.i("GettingPlannedMeals", "GettingPlannedMeals: " + plannedMeals.toArray().length);

            List<Meal> meals = new ArrayList<>();
            for (PlannedMealModel plannedMealModel : plannedMeals) {
                Meal meal = new Meal(plannedMealModel.getId(), plannedMealModel.getName(), plannedMealModel.getImageLink(), plannedMealModel.getMealDay() // Assuming all fav meals are marked as favorite
                );
                meals.add(meal);
            }
            Log.i("GettingPlannedMeals", "GettingPlannedMeals: ");
            return meals;
        });
    }

    @Override
    public Flowable<Boolean> isFavMeal(String mealId) {
        return mealsDAO.getFavMealCountById(mealId).map(count -> count > 0);
    }

    @Override
    public Flowable<Boolean> isPlannedMeal(String mealDay) {
        return mealsDAO.hasPlannedMeal(mealDay).map(count -> count > 0);
    }

    @Override
    public Flowable<List<Meal>> getPlannedMealsByDay(String dayName) {
        return mealsDAO.getPlannedMealsByDay(dayName).map(plannedMeals -> {
            List<Meal> meals = new ArrayList<>();
            for (PlannedMealModel plannedMeal : plannedMeals) {
                Meal meal = new Meal(plannedMeal.getId(), plannedMeal.getName(), plannedMeal.getImageLink(), plannedMeal.getMealDay());
                meals.add(meal);
            }
            return meals;
        });
    }
}
