package com.example.mealapp.meal_details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.mealapp.R;

public class MealDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        ImageView backButtonImgView = findViewById(R.id.BackButtonImageVie);
        backButtonImgView.setOnClickListener(v -> finish());

    }

}