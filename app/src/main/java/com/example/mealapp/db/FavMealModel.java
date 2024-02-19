package com.example.mealapp.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_meals")
public class FavMealModel {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "image_link")
    private String imageLink;
    @ColumnInfo(name = "image")
    private byte[] image;

    public FavMealModel(@NonNull String id, String name, String imageLink, byte[] image) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.image = image;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
