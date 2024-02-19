package com.example.mealapp.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity(tableName = "planned_meals", primaryKeys = {"id", "mealDay"})
public class PlannedMealModel {
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "image_link")
    private String imageLink;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @NonNull
    @ColumnInfo(name = "id")

    public String id;

    @NonNull
    @ColumnInfo(name = "mealDay")

    public String mealDay;

    public PlannedMealModel(String name,String imageLink,  @NonNull String id, @NonNull String mealDay) {
        this.name = name;

        this.id = id;
        this.imageLink = imageLink;
        this.mealDay = mealDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getMealDay() {
        return mealDay;
    }

    public void setMealDay(@NonNull String mealDay) {
        this.mealDay = mealDay;
    }
}

