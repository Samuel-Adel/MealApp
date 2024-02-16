package com.example.mealapp.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealapp.HomeActivity;
import com.example.mealapp.R;
import com.example.mealapp.login.model.ILoginRepository;
import com.example.mealapp.login.model.LoginRepository;
import com.example.mealapp.login.model.UserCredentials;
import com.example.mealapp.login.network.ILoginRemoteDataSource;
import com.example.mealapp.login.network.LoginRemoteDataSourceImpl;
import com.example.mealapp.login.presenter.ILoginPresenter;
import com.example.mealapp.login.presenter.LoginPresenterImpl;


public class LoginScreenFragment extends Fragment implements LoginView {
    private ILoginPresenter loginPresenter;
    private NavController navController;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private final String TAG = "Login";

    public LoginScreenFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        TextView signUpTextView = view.findViewById(R.id.signUpTextView);
        ILoginRemoteDataSource loginRemoteDataSource = LoginRemoteDataSourceImpl.getInstance();
        ILoginRepository loginRepository = LoginRepository.getInstance(loginRemoteDataSource);
        loginPresenter = new LoginPresenterImpl(this, loginRepository);
        navController = NavHostFragment.findNavController(this);
        progressBar = view.findViewById(R.id.progressBarLogin);
        email = view.findViewById(R.id.editTextEmailAddressLogin);
        password = view.findViewById(R.id.editTextPasswordLogin);

        signUpTextView.setOnClickListener(v ->
                navController.navigate(R.id.signupScreenFragment)

        );
        btnSignIn.setOnClickListener(v -> {
            if (validateInputs(email.getText().toString(), password.getText().toString())) {
                loginPresenter.loginWithUserCredentials(new UserCredentials(email.getText().toString(), password.getText().toString()));
                Log.i(TAG, "User Email"+email.getText().toString());
                Log.i(TAG, "User Password"+password.getText().toString());
            }

        });


    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            Toast.makeText(getContext(), "Empty email field!", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getContext(), "Empty password field!", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 8) {
            Toast.makeText(getContext(), "Password length < 8!", Toast.LENGTH_SHORT).show();
        }
        return !email.isEmpty() && password.length() >= 8;
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loggedInSuccessfully(String loggedInSuccessfullyMessage) {
        Toast.makeText(getContext(), loggedInSuccessfullyMessage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
//// Finish the current activity to prevent returning to it when pressing back
//        getActivity().finish();
    }

    @Override
    public void statusBarVisibilityStatus(boolean isVisible) {
        Log.i(TAG, "statusBarVisibilityStatus: "+isVisible);
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}