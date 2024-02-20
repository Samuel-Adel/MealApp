package com.example.mealapp.user.backup;

import com.example.mealapp.db.FavMealModel;
import com.example.mealapp.db.PlannedMealModel;

import java.util.List;

public class FireBaseUser {
    private String token;
    private List<FavMealModel> favMeals;
    private List<PlannedMealModel> plannedMeals;

    public FireBaseUser() {
    }


    public FireBaseUser(String token, List<FavMealModel> favMeals, List<PlannedMealModel> plannedMeals) {
        this.token = token;
        this.favMeals = favMeals;
        this.plannedMeals = plannedMeals;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<FavMealModel> getFavMeals() {
        return favMeals;
    }

    public void setFavMeals(List<FavMealModel> favMeals) {
        this.favMeals = favMeals;
    }

    public List<PlannedMealModel> getPlannedMeals() {
        return plannedMeals;
    }

    public void setPlannedMeals(List<PlannedMealModel> plannedMeals) {
        this.plannedMeals = plannedMeals;
    }
}

