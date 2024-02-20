package com.example.mealapp.home_screen.model;

import com.google.gson.annotations.SerializedName;

public class Country implements FilterItem {
    @SerializedName("strArea")

    private String name;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
