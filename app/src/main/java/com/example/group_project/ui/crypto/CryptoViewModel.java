package com.example.group_project.ui.crypto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CryptoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CryptoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a Crypto fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}