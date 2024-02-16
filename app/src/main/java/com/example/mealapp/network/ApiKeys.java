package com.example.mealapp.network;


import com.example.mealapp.home_screen.model.CategoryList;
import com.example.mealapp.home_screen.model.CountryList;
import com.example.mealapp.home_screen.model.IngredientList;
import com.example.mealapp.home_screen.model.MealsList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiKeys {
    @GET("random.php")
    Observable<MealsList> getSingleRandomMeal();
    @GET("list.php?c=list")
    Observable<CategoryList> getCategories();

    @GET("filter.php")
    Observable<MealsList> getMealsByCategory(@Query("c") String category);

    @GET("lookup.php")
    Observable<MealsList> getMealDetailsByID(@Query("i") String mealID);

    @GET("search.php")
    Observable<MealsList> getMealsBySearch(@Query("s") String query);

    @GET("list.php?a=list")
    Observable<CountryList> getAreas();

    @GET("filter.php")
    Observable<MealsList> getMealsByArea(@Query("a") String area);

    @GET("list.php?i=list")
    Observable<IngredientList> getIngredients();

    @GET("filter.php")
    Observable<MealsList> getMealsByIngredient(@Query("i") String ingredient);

}
