package com.example.group_project.ui.stocks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StocksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public StocksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a Stocks fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}