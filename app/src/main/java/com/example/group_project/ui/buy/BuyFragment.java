package com.example.group_project.ui.buy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentBuyBinding;
import com.example.group_project.databinding.FragmentStocksBinding;
import com.example.group_project.ui.buy.BuyFragment;

public class BuyFragment extends Fragment {

    private FragmentBuyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuyViewModel buyViewModel =
                new ViewModelProvider(this).get(BuyViewModel.class);

        binding = FragmentBuyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}