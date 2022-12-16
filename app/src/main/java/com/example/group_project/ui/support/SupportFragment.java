package com.example.group_project.ui.support;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentSupportBinding;

public class SupportFragment extends Fragment {

    private FragmentSupportBinding binding;
    Button supportBtn;
    TextView supportText;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSupportBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        supportText = view.findViewById(R.id.supportText);
        supportText.setText("Click button to redirect to our Website");
        supportBtn = view.findViewById(R.id.supportBtn);

        // Link to Contact Form on Website
        //https://year-3-group-project.web.app/contact.html

        supportBtn.setOnClickListener(v -> {
            String url = "https://year-3-group-project.web.app/contact.html";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}