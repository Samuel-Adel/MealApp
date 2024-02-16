package com.example.mealapp.login.presenter;

import com.example.mealapp.login.model.ILoginRepository;
import com.example.mealapp.login.model.UserCredentials;
import com.example.mealapp.network.NetworkCallBacks;
import com.example.mealapp.login.view.LoginView;

public class LoginPresenterImpl implements ILoginPresenter, NetworkCallBacks {
    private final LoginView loginView;
    private final ILoginRepository loginRepository;

    public LoginPresenterImpl(LoginView loginView, ILoginRepository loginRepository) {
        this.loginView = loginView;
        this.loginRepository = loginRepository;
    }

    @Override
    public void loginWithUserCredentials(UserCredentials userCredentials) {
        loginRepository.signInUser(this, userCredentials);
    }

    @Override
    public void onSuccessfulCallBack(String successMsg) {
        loginView.loggedInSuccessfully(successMsg);
    }

    @Override
    public void onFailureResult(String failureMsg) {
        loginView.showErrorMessage(failureMsg);
    }

    @Override
    public void callInProgressStatus(boolean status) {
        loginView.statusBarVisibilityStatus(status);
    }
}