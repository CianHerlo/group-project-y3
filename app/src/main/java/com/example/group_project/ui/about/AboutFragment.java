package com.example.group_project.ui.about;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;
    TextView aboutUsText;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        aboutUsText = view.findViewById(R.id.aboutUsText);
        aboutUsText.setText("3rd Year Group Project.\nThere's four students in our group. Aaron Canty, Alannah Cullinane Cooney, Cian Herlihy and Roisín Suarez.\n\nWe are currently studying in Munster Technological University (MTU) in Bishopstown, Cork. We have been tasked to create a mobile application that can trade stocks and cryptocurrencies. There will also be a website to match what we are doing in this project which will include a support chat bot which the app will not avail of. This application should predict pricing using algorithms and APIs. We hope you like our application.");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}