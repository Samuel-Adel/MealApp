package com.example.mealapp.home_screen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealapp.R;
import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.HomeScreenRepositoryImpl;
import com.example.mealapp.home_screen.model.IHomeScreenRepository;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.home_screen.network.HomeRemoteDataSourceImpl;
import com.example.mealapp.home_screen.network.IHomeRemoteDataSource;
import com.example.mealapp.home_screen.presenter.HomeScreenPresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class HomeScreenFragment extends Fragment implements OnFilterItemClickListener, HomeView {
    private HomeScreenAdapter<Category> homeScreenCategoryAdapter;
    private HomeScreenAdapter<Country> homeScreenCountryAdapter;
    private HomeScreenAdapter<Ingredient> homeScreenIngredientAdapter;
    private List<Category> categories;
    private List<Country> countries;
    private List<Ingredient> ingredients;
    private HomeScreenPresenterImpl homeScreenPresenter;

    private RecyclerView recyclerView;

    public HomeScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewFilter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeScreenCategoryAdapter = new HomeScreenAdapter<Category>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        homeScreenCountryAdapter = new HomeScreenAdapter<Country>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        homeScreenIngredientAdapter = new HomeScreenAdapter<Ingredient>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        IHomeRemoteDataSource homeRemoteDataSource = HomeRemoteDataSourceImpl.getInstance();
        IHomeScreenRepository homeScreenRepository = HomeScreenRepositoryImpl.getInstance(homeRemoteDataSource);
        homeScreenPresenter = new HomeScreenPresenterImpl(homeScreenRepository, this);
        recyclerView.setAdapter(homeScreenCategoryAdapter);
        homeScreenPresenter.getCategories();
        homeScreenCategoryAdapter.setCurrentFilter(CurrentFilter.category);
    }

    @Override
    public void changeFilterSelectedItemCountry(Country country) {

    }

    @Override
    public void changeFilterSelectedItemCategory(Category category) {
        Toast.makeText(getContext(), category.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void changeFilterSelectedItemIngredient(Ingredient ingredient) {

    }

    @Override
    public void ShowFilterWithCategories(List<Category> categoryList) {
        homeScreenCategoryAdapter.updateData(categoryList);
    }

    @Override
    public void ShowFilterWithCountries(List<Country> countries) {

    }

    @Override
    public void ShowFilterWithIngredients(List<Ingredient> ingredients) {

    }

    @Override
    public void ShowFilteredMeals(List<Meal> meals) {

    }
}