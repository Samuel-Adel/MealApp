package com.example.mealapp.user.logout;

import com.example.mealapp.db.FavMealModel;
import com.example.mealapp.db.PlannedMealModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class Logout {
    public static void saveUserDataToFirebase(String userToken, List<FavMealModel> favMeals, List<PlannedMealModel> plannedMeals) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").push();
        String userId = userRef.getKey();
        userRef.child("token").setValue(userToken);
        for (FavMealModel meal : favMeals) {
            userRef.child("fav_meals").push().setValue(meal);
        }
        for (PlannedMealModel meal : plannedMeals) {
            userRef.child("planned_meals").push().setValue(meal);
        }
    }

}
