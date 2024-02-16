package com.example.mealapp.home_screen.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IngredientList {
    @SerializedName("meals")
    private ArrayList<Ingredient> ingredients;
    private int total, skip, limit;

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> products) {
        this.ingredients = products;
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
