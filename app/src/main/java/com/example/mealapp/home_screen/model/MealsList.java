package com.example.mealapp.home_screen.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MealsList {
    @SerializedName("meals")

    private ArrayList<Meal> meals;
    private int total, skip, limit;

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> products) {
        this.meals = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
