package com.example.group_project.ui.support;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.databinding.FragmentSupportBinding;

public class SupportFragment extends Fragment {

    private FragmentSupportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SupportViewModel SupportViewModel =
                new ViewModelProvider(this).get(SupportViewModel.class);

        binding = FragmentSupportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.supportText;

        // Link to Contact Form on Website
        //https://year-3-group-project.web.app/contact.html

        SupportViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}