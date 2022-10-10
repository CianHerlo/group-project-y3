package com.example.group_project.ui.contact;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ContactViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a Contact fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}