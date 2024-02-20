package com.example.mealapp.home_screen.view;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealapp.MainActivity;
import com.example.mealapp.R;
import com.example.mealapp.db.MealsLocalDataBaseImpl;
import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.HomeScreenRepositoryImpl;
import com.example.mealapp.home_screen.model.IHomeScreenRepository;
import com.example.mealapp.home_screen.model.Ingredient;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.home_screen.network.HomeRemoteDataSourceImpl;
import com.example.mealapp.home_screen.network.IHomeRemoteDataSource;
import com.example.mealapp.home_screen.presenter.HomeScreenPresenterImpl;
import com.example.mealapp.meal_details.view.MealDetailsActivity;
import com.example.mealapp.user.UserLoginType;
import com.example.mealapp.user.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeScreenFragment extends Fragment implements OnFilterItemClickListener, HomeView {
    private HomeScreenFilterAdapter<Category> homeScreenCategoryAdapter;
    private HomeScreenFilterAdapter<Country> homeScreenCountryAdapter;
    private HomeScreenFilterAdapter<Ingredient> homeScreenIngredientAdapter;
    private HomeScreenMealsAdapter homeScreenMealsAdapter;
    private HomeScreenPresenterImpl homeScreenPresenter;
    private Disposable disposable;
    private RecyclerView recyclerViewFilter;
    private ProgressBar progressBar;
    private TextView browseByTextView;
    private CurrentType currentSearchType = CurrentType.category;

    public HomeScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeScreenCategoryAdapter = new HomeScreenFilterAdapter<>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        homeScreenCountryAdapter = new HomeScreenFilterAdapter<>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        homeScreenIngredientAdapter = new HomeScreenFilterAdapter<>(getActivity().getApplicationContext(), new ArrayList<>(), this);
        IMealsLocalDataBase favMealsLocalDataSource = MealsLocalDataBaseImpl.getInstance(getActivity().getApplicationContext());
        IHomeRemoteDataSource homeRemoteDataSource = HomeRemoteDataSourceImpl.getInstance(getActivity().getApplicationContext());
        IHomeScreenRepository homeScreenRepository = HomeScreenRepositoryImpl.getInstance(homeRemoteDataSource, favMealsLocalDataSource);
        homeScreenPresenter = new HomeScreenPresenterImpl(homeScreenRepository, this);
        ImageView filter = view.findViewById(R.id.filterIconView);
        EditText editTextSearch = view.findViewById(R.id.editTextSearch);
        TextView logout = view.findViewById(R.id.logOutTextVie);
        ConstraintLayout constraintLayout = view.findViewById(R.id.constraniedLayoutHomeScreen);
        UserType userType = UserType.getInstance();
        if (userType.getCurrentUserType() == UserLoginType.guestUser) {
            logout.setText(R.string.login);
        }
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
        RecyclerView recyclerViewMeals = view.findViewById(R.id.recyclerViewMeals);
        recyclerViewMeals.setLayoutManager(gridLayoutManager);
        recyclerViewMeals.setAdapter(homeScreenMealsAdapter);
        filter.setOnClickListener(v -> {
            showPopUpMenu(v, filter);
        });
        logout.setOnClickListener(v -> {
            if (userType.getCurrentUserType() != UserLoginType.guestUser) {
                homeScreenPresenter.logOut();
                Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();

        });
        constraintLayout.setOnTouchListener((v, event) -> {
            if (editTextSearch.hasFocus() && event.getAction() == MotionEvent.ACTION_DOWN) {
                Rect outRect = new Rect();
                editTextSearch.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    editTextSearch.clearFocus();
                }
            }
            return false;
        });
        Observable<String> textChangeObservable = Observable.create(emitter -> {
            editTextSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        });
        disposable = textChangeObservable.debounce(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(text -> {
            handleSearch(text);
        });
    }

    private void handleSearch(String text) {
        if (currentSearchType == CurrentType.country) {
            if (text.isEmpty()) {
                homeScreenPresenter.getCountries();
            } else {
                homeScreenPresenter.getMealsByCountry(new Country(text));
            }
        } else if (currentSearchType == CurrentType.category) {
            if (text.isEmpty()) {
                homeScreenPresenter.getCategories();
            } else {
                homeScreenPresenter.getMealsByCategory(new Category(text));

            }
        } else if (currentSearchType == CurrentType.ingredient) {
            if (text.isEmpty()) {
                homeScreenPresenter.getIngredients();
            } else {
                homeScreenPresenter.getMealsByIngredient(new Ingredient("", text, ""));
            }
        }
    }


    @Override
    public void changeFilterSelectedItemCountry(Country country) {
        homeScreenPresenter.getMealsByCountry(country);
    }

    @Override
    public void changeFilterSelectedItemCategory(Category category) {
        homeScreenPresenter.getMealsByCategory(category);

    }

    @Override
    public void changeFilterSelectedItemIngredient(Ingredient ingredient) {
        homeScreenPresenter.getMealsByIngredient(ingredient);
    }

    @Override
    public void addMealToFav(Meal meal) {
        homeScreenPresenter.addMealToFavourites(meal);
        Toast.makeText(getContext(), "Meal added to favourite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMealDetails(Meal meal) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("meal_id", meal.getId());
        startActivity(intent);
    }

    @Override
    public void showFilterWithCategories(List<Category> categoryList) {
        if (categoryList != null && !categoryList.isEmpty()) {
            homeScreenCategoryAdapter.updateData(categoryList);
            homeScreenCategoryAdapter.setCurrentFilter(CurrentFilter.category);
            homeScreenCategoryAdapter.setCurrentChoice(categoryList.get(0));
            homeScreenPresenter.getMealsByCategory(categoryList.get(0));
            recyclerViewFilter.setAdapter(homeScreenCategoryAdapter);
        } else {
            homeScreenCategoryAdapter.updateData(new ArrayList<>());
            recyclerViewFilter.setAdapter(homeScreenCategoryAdapter);
        }

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

    private void showPopUpMenu(View v, ImageView filter) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(), filter);
        popupMenu.getMenuInflater().inflate(R.menu.pop_menu_items, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.item_country) {
                homeScreenPresenter.getCountries();
                browseByTextView.setText(R.string.BrowseByCountry);
                currentSearchType = CurrentType.country;
            } else if (item.getItemId() == R.id.item_category) {
                browseByTextView.setText(R.string.BrowseByCategory);
                homeScreenPresenter.getCategories();
                currentSearchType = CurrentType.category;
            } else {
                browseByTextView.setText(R.string.BrowseByIngredient);
                homeScreenPresenter.getIngredients();
                currentSearchType = CurrentType.ingredient;
            }

            return true;
        });

        popupMenu.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}

enum CurrentType {
    country, category, ingredient
}