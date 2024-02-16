package com.example.mealapp.home_screen.model;

import com.google.gson.annotations.SerializedName;

public class Category implements FilterItem{
    @SerializedName("strCategory")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
