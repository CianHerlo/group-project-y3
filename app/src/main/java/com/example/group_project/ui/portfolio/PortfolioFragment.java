package com.example.group_project.ui.portfolio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.databinding.FragmentPortfolioBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PortfolioFragment extends Fragment {

    private FragmentPortfolioBinding binding;
    FirebaseAuth fireAuth;
    FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PortfolioViewModel portfolioViewModel =
                new ViewModelProvider(this).get(PortfolioViewModel.class);

        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FirebaseUser logged_user = FirebaseAuth.getInstance().getCurrentUser();
        assert logged_user != null;
        String logged_email = logged_user.getEmail();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}