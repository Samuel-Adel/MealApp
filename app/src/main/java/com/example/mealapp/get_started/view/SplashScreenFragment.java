package com.example.mealapp.get_started.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealapp.HomeActivity;
import com.example.mealapp.R;
import com.example.mealapp.get_started.model.ISplashScreenRepository;
import com.example.mealapp.get_started.model.SplashScreenRepository;
import com.example.mealapp.get_started.presenter.ISplashScreenPresenter;
import com.example.mealapp.get_started.presenter.SplashScreenPresenter;
import com.example.mealapp.user.UserSavedCredentialsManager;


public class SplashScreenFragment extends Fragment implements SplashScreenView {

    private UserSavedCredentialsManager userSavedCredentialsManager;
    private ISplashScreenPresenter splashScreenPresenter;
    private boolean hasLoggedInBefore;

    public SplashScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = NavHostFragment.findNavController(this);
        userSavedCredentialsManager = UserSavedCredentialsManager.getInstance(this.getContext());
        ISplashScreenRepository splashScreenRepository = SplashScreenRepository.getInstance(userSavedCredentialsManager);
        splashScreenPresenter = new SplashScreenPresenter(splashScreenRepository, this);
        splashScreenPresenter.checkUserSavedToken();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (hasLoggedInBefore) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    navController.navigate(R.id.getStartedFragment);
                }


            }
        }, 7000);

    }


    @Override
    public void showIfUserLoggedInBefore(String value) {
        hasLoggedInBefore = value != null;

    }
}