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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Meal meal);

    @Delete
    void deleteProduct(Meal meal);
}
