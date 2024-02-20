package com.example.mealapp.user.backup;

import com.example.mealapp.db.FavMealModel;
import com.example.mealapp.db.PlannedMealModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class FirebaseBackUp {
    public static void saveUserDataToFirebase(FireBaseUser user) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(user.getToken());
        userRef.setValue(user);
    }

}
