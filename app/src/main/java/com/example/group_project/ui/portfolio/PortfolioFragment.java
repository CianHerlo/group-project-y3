package com.example.group_project.ui.portfolio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.group_project.databinding.FragmentPortfolioBinding;

public class PortfolioFragment extends Fragment {

    private FragmentPortfolioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PortfolioViewModel portfolioViewModel =
                new ViewModelProvider(this).get(PortfolioViewModel.class);

        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}