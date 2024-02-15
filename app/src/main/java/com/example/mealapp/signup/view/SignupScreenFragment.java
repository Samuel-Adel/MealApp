package com.example.mealapp.signup.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealapp.R;
import com.example.mealapp.signup.model.ISignUpRepository;
import com.example.mealapp.signup.model.SignUpRepository;
import com.example.mealapp.signup.model.UserInfo;
import com.example.mealapp.signup.network.ISignUpRemoteDataSource;
import com.example.mealapp.signup.network.SignUpRemoteDataSourceImpl;
import com.example.mealapp.signup.presenter.ISignUpPresenter;
import com.example.mealapp.signup.presenter.SignUpPresenterImpl;


public class SignupScreenFragment extends Fragment implements SignUpView {
    private ISignUpPresenter signUpPresenter;
    private NavController navController;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private ProgressBar progressBar;

    public SignupScreenFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ISignUpRemoteDataSource signUpRemoteDataSource = SignUpRemoteDataSourceImpl.getInstance();
        ISignUpRepository signUpRepository = SignUpRepository.getInstance(signUpRemoteDataSource);
        signUpPresenter = new SignUpPresenterImpl(signUpRepository, this);
        navController = NavHostFragment.findNavController(this);
        TextView loginTextView = view.findViewById(R.id.loginTextView);
        Button btnSignUp = view.findViewById(R.id.btnSignup);
        email = view.findViewById(R.id.editTextEmailAddress);
        password = view.findViewById(R.id.editTextPassword);
        confirmPassword = view.findViewById(R.id.editTextConfirmPassword);
        progressBar = view.findViewById(R.id.signUpProgressBar);
        loginTextView.setOnClickListener(v -> {
            navController.navigate(R.id.loginScreenFragment);
        });
        btnSignUp.setOnClickListener(v -> {
            if (
                    validateInputs(email.getText().toString(), password.getText().toString(), confirmPassword.getText().toString())) {
                signUpPresenter.signUpNewUser(new UserInfo(email.getText().toString(), password.getText().toString()));
            }
        });
    }

    private boolean validateInputs(String email, String pass, String confirmPass) {
        if(email.isEmpty()){
            Toast.makeText(getContext(), "Empty email field!", Toast.LENGTH_SHORT).show();
        }else if(pass.isEmpty()||confirmPass.isEmpty()){
            Toast.makeText(getContext(), "Empty password field!", Toast.LENGTH_SHORT).show();
        }else if(pass.length()<8){
            Toast.makeText(getContext(), "Password length < 8!", Toast.LENGTH_SHORT).show();
        }else if( !pass.equals(confirmPass)){
            Toast.makeText(getContext(), "Passwords should match!", Toast.LENGTH_SHORT).show();
        }
        return !email.isEmpty() && pass.length() >= 8 && confirmPass.length() >= 8 && pass.equals(confirmPass);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void signedUpSuccessfully(String signedUpSuccessfully) {
        Toast.makeText(getContext(), signedUpSuccessfully, Toast.LENGTH_SHORT).show();
        navController.popBackStack();
    }

    @Override
    public void statusBarVisibilityStatus(boolean isVisible) {
        if (isVisible) {

            progressBar.setVisibility(View.VISIBLE);

        } else {
            progressBar.setVisibility(View.GONE);
        }

    }
}