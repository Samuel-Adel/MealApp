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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealapp.HomeActivity;
import com.example.mealapp.R;
import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.db.MealsLocalDataBaseImpl;
import com.example.mealapp.login.model.ILoginRepository;
import com.example.mealapp.login.model.LoginRepository;
import com.example.mealapp.login.model.UserCredentials;
import com.example.mealapp.user.UserSavedCredentialsManager;
import com.example.mealapp.login.network.ILoginRemoteDataSource;
import com.example.mealapp.login.network.LoginRemoteDataSourceImpl;
import com.example.mealapp.login.presenter.ILoginPresenter;
import com.example.mealapp.login.presenter.LoginPresenterImpl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginScreenFragment extends Fragment implements LoginView {
    private ILoginPresenter loginPresenter;
    private NavController navController;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private final String TAG = "Login";
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(UserCredentials.googleLogin)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this.getActivity(), gso);
        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        TextView signUpTextView = view.findViewById(R.id.signUpTextView);
        ILoginRemoteDataSource loginRemoteDataSource = LoginRemoteDataSourceImpl.getInstance(getContext().getApplicationContext(), getActivity());
        UserSavedCredentialsManager userSavedCredentialsManager = UserSavedCredentialsManager.getInstance(this.getContext());
        IMealsLocalDataBase mealsLocalDataBase = MealsLocalDataBaseImpl.getInstance(this.getContext());
        ILoginRepository loginRepository = LoginRepository.getInstance(loginRemoteDataSource, userSavedCredentialsManager, mealsLocalDataBase);
        loginPresenter = new LoginPresenterImpl(this, loginRepository);
        navController = NavHostFragment.findNavController(this);
        progressBar = view.findViewById(R.id.progressBarLogin);
        email = view.findViewById(R.id.editTextEmailAddressLogin);
        password = view.findViewById(R.id.editTextPasswordLogin);
        ImageView googleIcon = view.findViewById(R.id.googleIconBtn);
        ImageView guestUser = view.findViewById(R.id.loginGuestUserImgVie);
        signUpTextView.setOnClickListener(v ->
                navController.navigate(R.id.signupScreenFragment)

        );

        btnSignIn.setOnClickListener(v -> {
            if (validateInputs(email.getText().toString(), password.getText().toString())) {
                loginPresenter.loginWithUserCredentials(new UserCredentials(email.getText().toString(), password.getText().toString()));
                Log.i(TAG, "User Email" + email.getText().toString());
                Log.i(TAG, "User Password" + password.getText().toString());
            }

        });
        googleIcon.setOnClickListener(v -> {
            signIn();
        });
        guestUser.setOnClickListener(v -> {
            loginPresenter.loginAsGuest();
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
    public void loggedInSuccessfully(String userToken) {
        loginPresenter.saveUserCredentials(userToken);
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void loggedInAsGuest(String loggedInSuccessfullyMessage) {
        Toast.makeText(getContext(), loggedInSuccessfullyMessage, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void statusBarVisibilityStatus(boolean isVisible) {
        Log.i(TAG, "statusBarVisibilityStatus: " + isVisible);
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                loginPresenter.loginWithGoogle(account.getIdToken());
                //firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
}