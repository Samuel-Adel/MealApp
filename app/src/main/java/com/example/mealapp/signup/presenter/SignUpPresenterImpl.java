package com.example.mealapp.signup.presenter;

import com.example.mealapp.network.NetworkCallBacks;
import com.example.mealapp.signup.model.ISignUpRepository;
import com.example.mealapp.signup.model.UserInfo;
import com.example.mealapp.signup.view.SignUpView;

public class SignUpPresenterImpl implements ISignUpPresenter, NetworkCallBacks {
    private ISignUpRepository signUpRepository;
    private SignUpView signUpView;

    public SignUpPresenterImpl(ISignUpRepository signUpRepository, SignUpView signUpView) {
        this.signUpRepository = signUpRepository;
        this.signUpView = signUpView;
    }

    @Override
    public void signUpNewUser(UserInfo userInfo) {
        signUpRepository.signUpUser(this, userInfo);
    }

    @Override
    public void onSuccessfulCallBack(String successMsg) {
        signUpView.signedUpSuccessfully(successMsg);
    }

    @Override
    public void onFailureResult(String failureMsg) {
        signUpView.showErrorMessage(failureMsg);

    }

    @Override
    public void callInProgressStatus(boolean status) {
        signUpView.statusBarVisibilityStatus(status);
    }
}
