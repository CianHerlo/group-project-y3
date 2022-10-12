package com.example.group_project.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("3rd Year Group Project.\nThere's four students in our group. Aaron Canty, Alannah Cullinane Cooney, Cian Herlihy and Roisín Suarez.\n\nWe are currently studying in Munster Technological University (MTU) in Bishopstown, Cork. We have been tasked to create a mobile application that can trade stocks and cryptocurrencies. There will also be a website to match what we are doing in this project which will include a support chat bot which the app will not avail of. This application should predict pricing using algorithms and API's. We hope you like our application.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}