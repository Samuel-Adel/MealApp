package com.example.mealapp.home_screen.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient implements FilterItem {
    @SerializedName("idIngredient")
    private String id;
    @SerializedName("strIngredient")
    private String name;
    @SerializedName("strDescription")
    private String description;

    public Ingredient(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
