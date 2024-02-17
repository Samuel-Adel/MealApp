package com.example.mealapp.meal_details.network;

import android.util.Log;

import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.home_screen.model.MealsList;
import com.example.mealapp.network.ApiKeys;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealDetailsRemoteDataSourceImpl implements IMealDetailsRemoteDataSource{

    private static final String TAG = "MealDetailsRemoteDataSource";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static MealDetailsRemoteDataSourceImpl instance = null;
    private ApiKeys apiKeys;

    public static MealDetailsRemoteDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new MealDetailsRemoteDataSourceImpl();
        }
        return instance;

    }

    private MealDetailsRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiKeys = retrofit.create(ApiKeys.class);

    }

    @Override
    public void getMealDetails(IMealDetailsNetworkCallBack mealDetailsNetworkCallBack,String mealId) {
        mealDetailsNetworkCallBack.callInProgressStatus(true);
        Log.i(TAG, "makeNetworkCall: NetworkAPICallBack is not null");
        Observable<MealsList> categoryListObservable = apiKeys.getMealDetailsByID(mealId);
        categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    mealDetailsNetworkCallBack.callInProgressStatus(false);
                    Log.i(TAG, "onResponse: " + item.getMeals().get(0).getName());
                    mealDetailsNetworkCallBack.onSuccessfulResult(item.getMeals().get(0));
                },
                error -> {
                    mealDetailsNetworkCallBack.callInProgressStatus(false);
                    Log.i(TAG, "onFailure: " + error.getMessage());
                    mealDetailsNetworkCallBack.onFailureResult(error.getMessage());
                }
        );
    }
}
