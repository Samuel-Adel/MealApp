package com.example.mealapp.get_started_screen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealapp.R;
import com.example.mealapp.login.model.UserSavedCredentialsManager;

public class GetStartedFragment extends Fragment {

    public GetStartedFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_get_started, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = NavHostFragment.findNavController(this);
        Button btnGetStarted = view.findViewById(R.id.btnGetStarted);
        TextView btnLogin = view.findViewById(R.id.loginTextViewGetStarted);
        btnGetStarted.setOnClickListener(v -> {
            navController.navigate(R.id.signupScreenFragment);
        });
        btnLogin.setOnClickListener(v -> {
            navController.navigate(R.id.loginScreenFragment);
        });
    }
}