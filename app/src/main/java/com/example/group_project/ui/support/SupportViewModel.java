package com.example.group_project.ui.support;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SupportViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SupportViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Click button to redirect to support chat");
    }

    public LiveData<String> getText() {
        return mText;
    }
}