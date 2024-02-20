package com.example.mealapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface MealsDAO {

    @Query("SELECT * FROM fav_meals")
    Flowable<List<FavMealModel>> getAllFavMeals();

    @Query("SELECT COUNT(*) FROM fav_meals WHERE id = :id")
    Flowable<Integer> getFavMealCountById(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavMeal(FavMealModel favMeal);

    @Query("DELETE FROM fav_meals WHERE id = :id")
    void deleteFavMealById(String id);

    @Query("SELECT COUNT(*) FROM planned_meals WHERE id = :id")
    Flowable<Integer> hasPlannedMeal(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlannedMeal(PlannedMealModel plannedMeal);

    @Query("SELECT * FROM planned_meals WHERE mealDay = :day")
    Flowable<List<PlannedMealModel>> getPlannedMealsByDay(String day);

    @Query("DELETE FROM planned_meals WHERE id = :id AND mealDay = :mealDay")
    void deletePlannedMealById(String id, String mealDay);
    @Query("SELECT * FROM planned_meals")
    Flowable<List<PlannedMealModel>> getAllPlannedMeals();
}
