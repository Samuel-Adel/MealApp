package com.example.mealapp.home_screen.network;

import android.util.Log;

import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.CategoryList;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.network.ApiKeys;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
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

    }

    @Override
    public void getMealsByCountry(IHomeNetworkCallBacks networkCallBacks, Country country) {

    }

    @Override
    public void getMealsByIngredient(IHomeNetworkCallBacks networkCallBacks, Ingredient ingredient) {

    }

    @Override
    public void getCategories(IHomeNetworkCallBacks networkCallBacks) {
        if (networkCallBacks == null) {
            Log.e(TAG, "makeNetworkCall: NetworkAPICallBack is null");
            return;
        } else {
            Log.i(TAG, "makeNetworkCall: NetworkAPICallBack is not null");
        }
        Observable<CategoryList> categoryListObservable = apiKeys.getCategories();
      //  Disposable subscribe =
                categoryListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    Log.i(TAG, "onResponse: " + item.getCategories().get(1).getName());
                    networkCallBacks.onCategoriesSuccessfulCallBack(item.getCategories());
                },
                error -> {
                    Log.i(TAG, "onFailure: "+error.getMessage());
                    networkCallBacks.onFailureResult(error.getMessage());
                }
        );
//        subscribe.dispose();
    }

    @Override
    public void getCountries(IHomeNetworkCallBacks networkCallBacks) {

    }

    @Override
    public void getIngredients(IHomeNetworkCallBacks networkCallBacks) {

    }
}
