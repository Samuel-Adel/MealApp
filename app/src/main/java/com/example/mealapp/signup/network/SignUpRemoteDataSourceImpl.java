package com.example.mealapp.signup.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mealapp.network.NetworkCallBacks;
import com.example.mealapp.signup.model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpRemoteDataSourceImpl implements ISignUpRemoteDataSource {
    private static final String TAG = "SignUpRemoteDataSource";
    public static SignUpRemoteDataSourceImpl instance = null;
    private FirebaseAuth mAuth;

    public static SignUpRemoteDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new SignUpRemoteDataSourceImpl();
        }
        return instance;

    }

    private SignUpRemoteDataSourceImpl() {
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void makeNetworkCall(NetworkCallBacks networkCallBacks, UserInfo userInfo) {
        networkCallBacks.callInProgressStatus(true);
        mAuth.createUserWithEmailAndPassword(userInfo.getEmail(), userInfo.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        networkCallBacks.callInProgressStatus(false);

                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            networkCallBacks.onSuccessfulCallBack("SignedUp Successfully");
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            networkCallBacks.onFailureResult(task.getException().getMessage());
                        }
                    }
                });
    }
}
