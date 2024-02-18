package com.example.mealapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface MealsDAO {
    @Query("SELECT * FROM meals")
    Flowable<List<Meal>> getAllProducts();

    @Query("SELECT * FROM meals WHERE isFavorite = 1")
    Flowable<List<Meal>> getFavoriteMeals();

    @Query("SELECT EXISTS (SELECT 1 FROM meals WHERE id = :mealId AND isFavorite = 1)")
    Flowable<Boolean> isMealFavorite(String mealId);

    @Query("SELECT * FROM meals WHERE mealDay = 'saturday'")
    Flowable<List<Meal>> getSaturdayMeals();

    @Query("SELECT * FROM meals WHERE mealDay = 'sunday'")
    Flowable<List<Meal>> getSundayMeals();

    @Query("SELECT * FROM meals WHERE mealDay = 'monday'")
    Flowable<List<Meal>> getMondayMeals();

    @Query("SELECT * FROM meals WHERE mealDay = 'tuesday'")
    Flowable<List<Meal>> getTuesdayMeals();

    @Query("SELECT * FROM meals WHERE mealDay = 'wednesday'")
    Flowable<List<Meal>> getWednesdayMeals();

    @Query("SELECT * FROM meals WHERE mealDay = 'thursday'")
    Flowable<List<Meal>> getThursdayMeals();

    @Query("SELECT * FROM meals WHERE mealDay = 'friday'")
    Flowable<List<Meal>> getFridayMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Meal meal);

    @Delete
    void deleteProduct(Meal meal);
}
