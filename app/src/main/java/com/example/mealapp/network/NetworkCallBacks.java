package com.example.mealapp.network;

import java.util.List;

public interface NetworkCallBacks {
    public void onSuccessfulCallBack(String successMsg);
    public void onFailureResult(String failureMsg);
    public void callInProgressStatus(boolean status);
}
