package com.example.group_project.ui.portfolio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PortfolioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PortfolioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a Portfolio fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}