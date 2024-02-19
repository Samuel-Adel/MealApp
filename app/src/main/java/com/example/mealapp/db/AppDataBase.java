package com.example.mealapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mealapp.home_screen.model.Meal;

@Database(entities = {FavMealModel.class,PlannedMealModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;

    public abstract MealsDAO geProductsDAO();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "meals")
                    .build();
        }
        return instance;
    }
}
