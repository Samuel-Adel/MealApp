package com.example.mealapp.meal_plans.network;

import android.util.Log;

import com.example.mealapp.home_screen.model.MealsList;
import com.example.mealapp.home_screen.network.HomeRemoteDataSourceImpl;
import com.example.mealapp.network.ApiKeys;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealPlansRemoteDataSourceImpl implements IMealPlansRemoteDataSource {
    private static final String TAG = "MealPlansRemoteDataSource";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static MealPlansRemoteDataSourceImpl instance = null;
    private ApiKeys apiKeys;

    public static MealPlansRemoteDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new MealPlansRemoteDataSourceImpl();
        }
        return instance;

    }

    private MealPlansRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiKeys = retrofit.create(ApiKeys.class);

    }


    @Override
    public void getRandomMeal(IMealPlansNetworkCallBacks mealPlansNetworkCallBacks) {
        mealPlansNetworkCallBacks.callInProgressStatus(true);
        Log.i(TAG, "makeNetworkCall: NetworkAPICallBack is not null");
        Observable<MealsList> categoryListObservable = apiKeys.getSingleRandomMeal();
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    mealPlansNetworkCallBacks.callInProgressStatus(false);
//                    Log.i(TAG, "onResponse: " + item.getMeals().get(0).getName());
                    mealPlansNetworkCallBacks.onSuccessfulCallBack(item.getMeals().get(0));
                },
                error -> {
                    mealPlansNetworkCallBacks.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    mealPlansNetworkCallBacks.onFailureResult(error.getMessage());
                }
        );
    }
}
