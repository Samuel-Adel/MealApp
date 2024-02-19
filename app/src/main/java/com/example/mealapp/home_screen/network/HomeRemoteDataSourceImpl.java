package com.example.mealapp.home_screen.network;

import android.content.Context;
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

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeRemoteDataSourceImpl implements IHomeRemoteDataSource {
    private Context context;
    private static final String TAG = "HomeScreenRemoteDataSource";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static HomeRemoteDataSourceImpl instance = null;
    private ApiKeys apiKeys;

    public static HomeRemoteDataSourceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new HomeRemoteDataSourceImpl(context);
        }
        return instance;

    }

    private HomeRemoteDataSourceImpl(Context context) {
        this.context = context;
        int size = 10 * 1024 * 1024;
        Cache cache = new Cache(this.context.getCacheDir(), size);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
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
//                    Log.i(TAG, "onResponse: " + item.getMeals().get(0).getName());
                    networkCallBacks.onMealsSuccessfulCallBack(item.getMeals() == null ? new ArrayList<>() : item.getMeals());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult("There is no network");
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
//                    Log.i(TAG, "onResponse: " + item.getMeals().get(0).getName());
                    networkCallBacks.onMealsSuccessfulCallBack(item.getMeals() == null ? new ArrayList<>() : item.getMeals());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult("There is no network");
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
//                    Log.i(TAG, "onResponse: " + item.getMeals().get(0).getName());
                    networkCallBacks.onMealsSuccessfulCallBack(item.getMeals() == null ? new ArrayList<>() : item.getMeals());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult("There is no network");
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
//                    Log.i(TAG, "onResponse: " + item.getCategories().get(0).getName());
                    networkCallBacks.onCategoriesSuccessfulCallBack(item.getCategories());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult("There is no network");
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
//                    Log.i(TAG, "onResponse: " + item.getCountries().get(0).getName());
                    networkCallBacks.onCountriesSuccessfulCallBack(item.getCountries());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult("There is no network");
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
//                    Log.i(TAG, "onResponse: " + item.getIngredients().get(0).getName());
                    networkCallBacks.onIngredientsSuccessfulCallBack(item.getIngredients());
                },
                error -> {
                    networkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    networkCallBacks.onFailureResult("There is no network");
                }
        );
    }
}
