package com.example.mealapp.home_screen.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealapp.HomeActivity;
import com.example.mealapp.R;
import com.example.mealapp.db.FavMealsLocalDataSourceImpl;
import com.example.mealapp.db.IFavMealsLocalDataSource;
import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.HomeScreenRepositoryImpl;
import com.example.mealapp.home_screen.model.IHomeScreenRepository;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.home_screen.network.HomeRemoteDataSourceImpl;
import com.example.mealapp.home_screen.network.IHomeRemoteDataSource;
import com.example.mealapp.home_screen.presenter.HomeScreenPresenterImpl;
import com.example.mealapp.meal_details.MealDetailsFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeScreenFragment extends Fragment implements OnFilterItemClickListener, HomeView {
    private HomeScreenFilterAdapter<Category> homeScreenCategoryAdapter;
    private HomeScreenFilterAdapter<Country> homeScreenCountryAdapter;
    private HomeScreenFilterAdapter<Ingredient> homeScreenIngredientAdapter;
    private HomeScreenMealsAdapter homeScreenMealsAdapter;
    private List<Category> categories;
    private List<Country> countries;
    private List<Ingredient> ingredients;
    private HomeScreenPresenterImpl homeScreenPresenter;

    private RecyclerView recyclerViewFilter;
    private ProgressBar progressBar;
    private TextView browseByTextView;

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
        homeScreenCategoryAdapter = new HomeScreenFilterAdapter<>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        homeScreenCountryAdapter = new HomeScreenFilterAdapter<>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        homeScreenIngredientAdapter = new HomeScreenFilterAdapter<>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        IFavMealsLocalDataSource favMealsLocalDataSource = FavMealsLocalDataSourceImpl.getInstance(getActivity().getApplicationContext());
        IHomeRemoteDataSource homeRemoteDataSource = HomeRemoteDataSourceImpl.getInstance();
        IHomeScreenRepository homeScreenRepository = HomeScreenRepositoryImpl.getInstance(homeRemoteDataSource, favMealsLocalDataSource);
        homeScreenPresenter = new HomeScreenPresenterImpl(homeScreenRepository, this);

        ImageView filter = view.findViewById(R.id.filterIconView);
        progressBar = view.findViewById(R.id.progressBarHome);
        browseByTextView = view.findViewById(R.id.textViewBrowseByCategory);
        recyclerViewFilter = view.findViewById(R.id.recyclerViewFilter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewFilter.setLayoutManager(linearLayoutManager);
        recyclerViewFilter.setAdapter(homeScreenCategoryAdapter);
        homeScreenPresenter.getCategories();


        homeScreenMealsAdapter = new HomeScreenMealsAdapter(getActivity().getApplicationContext(), new ArrayList<>(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        RecyclerView recyclerViewMeals = view.findViewById(R.id.recyclerVieMeals);
        recyclerViewMeals.setLayoutManager(gridLayoutManager);
        recyclerViewMeals.setAdapter(homeScreenMealsAdapter);
        filter.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), filter);
            popupMenu.getMenuInflater().inflate(R.menu.pop_menu_items, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.item_country) {
                    homeScreenPresenter.getCountries();
                    browseByTextView.setText(R.string.BrowseByCountry);
                    Log.i("PopUpMenu", "onViewCreated: " + "Country Pressed");
                } else if (item.getItemId() == R.id.item_category) {
                    Log.i("PopUpMenu", "onViewCreated: " + "Category Pressed");
                    browseByTextView.setText(R.string.BrowseByCategory);
                    homeScreenPresenter.getCategories();
                } else {
                    Log.i("PopUpMenu", "onViewCreated: " + "Ingredient Pressed");
                    browseByTextView.setText(R.string.BrowseByIngredient);
                    homeScreenPresenter.getIngredients();
                }

                return true;
            });

            popupMenu.show();
        });
    }

    @Override
    public void changeFilterSelectedItemCountry(Country country) {
        homeScreenPresenter.getMealsByCountry(country);
    }

    @Override
    public void changeFilterSelectedItemCategory(Category category) {
        Toast.makeText(getContext(), category.getName(), Toast.LENGTH_SHORT).show();
        homeScreenPresenter.getMealsByCategory(category);

    }

    @Override
    public void changeFilterSelectedItemIngredient(Ingredient ingredient) {
        homeScreenPresenter.getMealsByIngredient(ingredient);
    }

    @Override
    public void addMealToFav(Meal meal) {
        homeScreenPresenter.addMealToFavourites(meal);
        Toast.makeText(getContext(), meal.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMealDetails(Meal meal) {
        Toast.makeText(getContext(), meal.getId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MealDetailsFragment.class);
        startActivity(intent);
        //// Finish the current activity to prevent returning to it when pressing back
//        getActivity().finish();
    }

    @Override
    public void showFilterWithCategories(List<Category> categoryList) {
        homeScreenCategoryAdapter.updateData(categoryList);
        homeScreenCategoryAdapter.setCurrentFilter(CurrentFilter.category);
        homeScreenCategoryAdapter.setCurrentChoice(categoryList.get(0));
        homeScreenPresenter.getMealsByCategory(categoryList.get(0));
        recyclerViewFilter.setAdapter(homeScreenCategoryAdapter);
    }

    @Override
    public void showFilterWithCountries(List<Country> countries) {
        homeScreenCountryAdapter.updateData(countries);
        homeScreenCountryAdapter.setCurrentFilter(CurrentFilter.country);
        homeScreenCountryAdapter.setCurrentChoice(countries.get(0));
        homeScreenPresenter.getMealsByCountry(countries.get(0));
        recyclerViewFilter.setAdapter(homeScreenCountryAdapter);

    }

    @Override
    public void showFilterWithIngredients(List<Ingredient> ingredients) {
        homeScreenIngredientAdapter.updateData(ingredients);
        homeScreenIngredientAdapter.setCurrentChoice(ingredients.get(0));
        homeScreenPresenter.getMealsByIngredient(ingredients.get(0));
        homeScreenIngredientAdapter.setCurrentFilter(CurrentFilter.ingredient);
        recyclerViewFilter.setAdapter(homeScreenIngredientAdapter);

    }

    @Override
    public void showFilteredMeals(List<Meal> meals) {
        homeScreenMealsAdapter.updateData(meals);
    }


    @Override
    public void statusBarVisibilityStatus(boolean isVisible) {
        Log.i("Home ProgressBar", "statusBarVisibilityStatus: " + isVisible);
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }


}