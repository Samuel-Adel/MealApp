package com.example.mealapp.home_screen.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "meals")

public class Meal {
    @SerializedName("idMeal")
    @PrimaryKey
    @NonNull
    private String id;
    @SerializedName("strMeal")
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("strCategory")


    private String category;
    @SerializedName("strArea")

    private String country;
    @SerializedName("strInstructions")

    private String instructions;
    @SerializedName("strMealThumb")

    private String imageLink;
    @SerializedName("strYoutube")

    private String videoLink;


    public Meal(String id, String name, String category, String country,
                String instructions, String imageLink, String videoLink) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.country = country;
        this.instructions = instructions;
        this.imageLink = imageLink;
        this.videoLink = videoLink;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
