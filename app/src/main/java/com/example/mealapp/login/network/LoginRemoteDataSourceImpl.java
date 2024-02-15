package com.example.mealapp.login.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealapp.login.model.UserCredentials;
import com.example.mealapp.network.NetworkCallBacks;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRemoteDataSourceImpl implements ILoginRemoteDataSource {
    private static final String TAG = "LoginRemoteDataSource";
    public static LoginRemoteDataSourceImpl instance = null;
    private final FirebaseAuth mAuth;

    public static LoginRemoteDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new LoginRemoteDataSourceImpl();
        }
        return instance;

    }

    private LoginRemoteDataSourceImpl() {
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void makeNetworkCall(NetworkCallBacks networkCallBacks, UserCredentials userCredentials) {
        networkCallBacks.callInProgressStatus(true);
        mAuth.signInWithEmailAndPassword(userCredentials.getUserEmail(), userCredentials.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        networkCallBacks.callInProgressStatus(false);
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
                            networkCallBacks.onSuccessfulCallBack("Logged in Successfully");
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            networkCallBacks.onFailureResult(task.getException().getMessage());

                        }
                    }
                });
    }
}
