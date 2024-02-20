package com.example.mealapp.login.network;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealapp.login.model.UserCredentials;
import com.example.mealapp.network.NetworkCallBacks;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginRemoteDataSourceImpl implements ILoginRemoteDataSource {
    private static final String TAG = "LoginRemoteDataSource";

    public static LoginRemoteDataSourceImpl instance = null;
    private final FirebaseAuth mAuth;


    public static LoginRemoteDataSourceImpl getInstance(Context context, Activity activity) {
        if (instance == null) {
            instance = new LoginRemoteDataSourceImpl(context, activity);
        }
        return instance;

    }

    private LoginRemoteDataSourceImpl(Context context, Activity activity) {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(UserCredentials.googleLogin).requestEmail().build();
    }

    @Override
    public void makeNetworkCall(NetworkCallBacks networkCallBacks, UserCredentials userCredentials) {
        networkCallBacks.callInProgressStatus(true);
        mAuth.signInWithEmailAndPassword(userCredentials.getUserEmail(), userCredentials.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                networkCallBacks.callInProgressStatus(false);
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    if (user != null) {
                        user.getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                            @Override
                            public void onSuccess(GetTokenResult result) {
                                String token = result.getToken();
                                Log.i(TAG, "onSuccess: " + token);
                                networkCallBacks.onSuccessfulCallBack(token);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "Failed to get token.", e);
                                networkCallBacks.onFailureResult("Failed to get token.");
                            }
                        });
                    }
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    networkCallBacks.onFailureResult(task.getException().getMessage());

                }
            }
        });
    }

    @Override
    public void signUpWithGoogle(NetworkCallBacks networkCallBacks, String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        user.getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                            @Override
                            public void onSuccess(GetTokenResult result) {
                                String token = result.getToken();
                                Log.i(TAG, "onSuccess: " + token);
                                networkCallBacks.onSuccessfulCallBack(token);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle failure to retrieve token
                                Log.e(TAG, "Failed to get token.", e);
                                networkCallBacks.onFailureResult("Failed to get token.");
                            }
                        });
                    }
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    networkCallBacks.onFailureResult(task.getException().getMessage());

                }
            }
        });
    }
}
