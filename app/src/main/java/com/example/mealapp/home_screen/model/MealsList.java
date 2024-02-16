package com.example.mealapp.home_screen.model;

import java.util.ArrayList;

public class MealsList {
    private ArrayList<Meal> meals;
    private int total, skip,limit;

    public ArrayList<Meal> getProducts() {
        return meals;
    }

    public void setProducts(ArrayList<Meal> products) {
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
