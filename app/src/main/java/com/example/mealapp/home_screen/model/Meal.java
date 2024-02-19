package com.example.mealapp.home_screen.model;


import com.google.gson.annotations.SerializedName;


public class Meal {
    @SerializedName("idMeal")
    private String id;
    @SerializedName("strMeal")
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
    private byte[] image;
    private boolean isFav;
    private String mealDay;

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public String getMealDay() {
        return mealDay;
    }

    public void setMealDay(String mealDay) {
        this.mealDay = mealDay;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

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

    public Meal(String id, String name, String imageLink, byte[] image, boolean isFav) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.imageLink = imageLink;
        this.isFav = isFav;
    }

    public Meal(String id, String name, String imageLink, byte[] image, String mealDay) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.mealDay = mealDay;
        this.imageLink = imageLink;
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
