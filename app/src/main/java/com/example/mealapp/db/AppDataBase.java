package com.example.mealapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavMealModel.class,PlannedMealModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;

    public abstract MealsDAO getMealsDAO();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "meals")
                    .build();
        }
        return instance;
    }
    public static void clearDatabase() {
        instance.clearAllTables();
    }
}
