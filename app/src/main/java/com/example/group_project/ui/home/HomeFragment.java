package com.example.group_project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group_project.Payment;
import com.example.group_project.R;
import com.example.group_project.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Button addFundsBTN;
    TextView homeText1, homeText2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addFundsBTN = view.findViewById(R.id.addFundsBTN);
        homeText1 = view.findViewById(R.id.homeText1);
        homeText2 = view.findViewById(R.id.homeText2);

        homeText1.setText("At FinTech Trader, we pride ourselves in giving trading services to beginners. It is designed to be a basic UI that people find very easy to navigate. This allows us to focus on bringing more and more features that matter most to you!");
        homeText2.setText("Investing can be rewarding but it is not without risk. You can lose (a part of) your deposit. We are open and transparent about the risks that come with investing. Before you start to invest, there are a number of factors to consider. It helps to think about how much risk you are willing to take and which products are best suited for your needs.");


        addFundsBTN.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Payment.class);
            v.getContext().startActivity(intent);
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}