package com.example.group_project.ui.portfolio;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.databinding.FragmentPortfolioBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;

public class PortfolioFragment extends Fragment {

    private FragmentPortfolioBinding binding;
    FirebaseFirestore firestore;
    Map<String, Object> userInfo; // declare the global variable
    TextView adobeOwned, amazonOwned, appleOwned, googleOwned, microsoftOwned, bitcoinOwned, ethereumOwned, totalValuePortfolio;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PortfolioViewModel portfolioViewModel =
                new ViewModelProvider(this).get(PortfolioViewModel.class);

        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        firestore = FirebaseFirestore.getInstance();
        FirebaseUser logged_user = FirebaseAuth.getInstance().getCurrentUser();
        assert logged_user != null;
        String logged_email = logged_user.getEmail();


        CollectionReference customerWalletsRef = firestore.collection("customer_wallets");
        Query query = customerWalletsRef.whereEqualTo("Email", logged_email);
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // task was successful, now you can iterate over the documents in the query snapshot
                for (DocumentSnapshot doc : task.getResult()) {
                    if (doc.get("Email").equals(logged_email)) {
                        // found the matching document
                        userInfo = doc.getData();
                    }
                }
            } else {
                // task was not successful, handle the error
                Log.w(TAG, "Error with query");
            }
        });


//        Toast.makeText(getContext(), userInfo.get("Email").toString(), Toast.LENGTH_SHORT).show();
//        adobeOwned.setText(userInfo.get("Adobe").toString());



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}