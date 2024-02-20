package com.example.mealapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mealapp.user.UserLoginType;
import com.example.mealapp.user.UserType;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private NavController navController;
    private UserType userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        userType = UserType.getInstance();
        MenuItem homeItem = bottomNavigationView.getMenu().findItem(R.id.homeScreenFragment);
        homeItem.setChecked(true);
        int selectedColor = Color.parseColor("#E4AC4F");
        int unSelected = Color.parseColor("#827979");

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{-android.R.attr.state_checked}
                },
                new int[]{
                        selectedColor,
                        unSelected
                }
        );
        navController = Navigation.findNavController(this, R.id.app_nav_host_fragment);
        bottomNavigationView.setItemIconTintList(colorStateList);
        bottomNavigationView.setItemTextColor(colorStateList);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (userType.getCurrentUserType() == UserLoginType.guestUser && (item.getItemId() == R.id.favMealsScreenFragment || item.getItemId() == R.id.mealsPlanFragment)) {
                Toast.makeText(getApplicationContext(), "Please login to access these features", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return NavigationUI.onNavDestinationSelected(item, navController);
            }
        });

//        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}