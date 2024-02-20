package com.example.mealapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        List<FavMealModel> favMeals = new ArrayList<>();
//        FavMealModel favMealModel = new FavMealModel("12", "Samuel", "ImgLink");
//        favMeals.add(favMealModel);
//        favMeals.add(favMealModel);
//        List<PlannedMealModel> plannedMeals = new ArrayList<>();
//        PlannedMealModel plannedMealModel = new PlannedMealModel("12", "Samuel", "ImgLink", "Saturday");
//        plannedMeals.add(plannedMealModel);
//        plannedMeals.add(plannedMealModel);
//        FireBaseUser user = new FireBaseUser("dummyToken", favMeals, plannedMeals);




    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}