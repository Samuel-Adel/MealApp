package com.example.mealapp.home_screen.network;

import android.util.Log;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.CategoryList;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.CountryList;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.IngredientList;
import com.example.mealapp.home_screen.model.MealsList;
import com.example.mealapp.network.ApiKeys;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeRemoteDataSourceImpl implements IHomeRemoteDataSource {
    private static final String TAG = "ProductClient";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static HomeRemoteDataSourceImpl instance = null;
    private ApiKeys apiKeys;

    public static HomeRemoteDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new HomeRemoteDataSourceImpl();
        }
        return instance;

    }

    private HomeRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiKeys = retrofit.create(ApiKeys.class);

    }

    @Override
    public void getMealsByCategory(IHomeNetworkCallBacks networkCallBacks, Category category) {
        networkCallBacks.callInProgressStatus(true);
        Log.i(TAG, "makeNetworkCall: NetworkAPICallBack is not null");
        Observable<MealsList> categoryListObservable = apiKeys.getMealsByCategory(category.getName());
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onResponse: " + item.getMeals().get(1).getName());
                    networkCallBacks.onMealsSuccessfulCallBack(item.getMeals());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult(error.getMessage());
                }
        );
    }

    @Override
    public void getMealsByCountry(IHomeNetworkCallBacks networkCallBacks, Country country) {
        networkCallBacks.callInProgressStatus(true);
        Log.i(TAG, "makeNetworkCall: NetworkAPICallBack is not null");
        Observable<MealsList> categoryListObservable = apiKeys.getMealsByCountry(country.getName());
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onResponse: " + item.getMeals().get(1).getName());
                    networkCallBacks.onMealsSuccessfulCallBack(item.getMeals());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult(error.getMessage());
                }
        );

    }

    @Override
    public void getMealsByIngredient(IHomeNetworkCallBacks networkCallBacks, Ingredient ingredient) {
        networkCallBacks.callInProgressStatus(true);
        Log.i(TAG, "makeNetworkCall: NetworkAPICallBack is not null");
        Observable<MealsList> categoryListObservable = apiKeys.getMealsByIngredient(ingredient.getName());
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onResponse: " + item.getMeals().get(1).getName());
                    networkCallBacks.onMealsSuccessfulCallBack(item.getMeals());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult(error.getMessage());
                }
        );
    }

    @Override
    public void getCategories(IHomeNetworkCallBacks networkCallBacks) {
        networkCallBacks.callInProgressStatus(true);
        Log.i(TAG, "makeNetworkCall: NetworkAPICallBack is not null");
        Observable<CategoryList> categoryListObservable = apiKeys.getCategories();
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onResponse: " + item.getCategories().get(1).getName());
                    networkCallBacks.onCategoriesSuccessfulCallBack(item.getCategories());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult(error.getMessage());
                }
        );
    }

    @Override
    public void getCountries(IHomeNetworkCallBacks networkCallBacks) {
        networkCallBacks.callInProgressStatus(true);
        Observable<CountryList> categoryListObservable = apiKeys.getCountries();
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(

                item -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onResponse: " + item.getCountries().get(1).getName());
                    networkCallBacks.onCountriesSuccessfulCallBack(item.getCountries());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult(error.getMessage());
                }
        );
    }

    @Override
    public void getIngredients(IHomeNetworkCallBacks networkCallBacks) {
        networkCallBacks.callInProgressStatus(true);
        Observable<IngredientList> categoryListObservable = apiKeys.getIngredients();
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onResponse: " + item.getIngredients().get(1).getName());
                    networkCallBacks.onIngredientsSuccessfulCallBack(item.getIngredients());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult(error.getMessage());
                }
        );
    }
}
