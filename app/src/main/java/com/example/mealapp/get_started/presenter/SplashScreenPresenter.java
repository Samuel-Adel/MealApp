package com.example.mealapp.get_started.presenter;

import com.example.mealapp.get_started.model.ISplashScreenRepository;
import com.example.mealapp.get_started.view.SplashScreenView;

public class SplashScreenPresenter implements ISplashScreenPresenter {
    private ISplashScreenRepository getStartedRepository;
    private SplashScreenView getStartedView;

    public SplashScreenPresenter(ISplashScreenRepository getStartedRepository, SplashScreenView getStartedView) {
        this.getStartedRepository = getStartedRepository;
        this.getStartedView = getStartedView;
    }

    @Override
    public void checkUserSavedToken() {
        getStartedView.showIfUserLoggedInBefore(getStartedRepository.checkUserSavedToken());
    }
}
