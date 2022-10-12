package com.example.group_project.ui.buy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BuyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a Buy fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}