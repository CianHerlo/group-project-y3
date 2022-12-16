package com.example.group_project.ui.portfolio;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentPortfolioBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;
import java.util.Objects;

public class PortfolioFragment extends Fragment {

    private FragmentPortfolioBinding binding;
    FirebaseFirestore firestore;
    Map<String, Object> userInfo;
    TextView adobeOwned, amazonOwned, appleOwned, googleOwned, microsoftOwned, bitcoinOwned, ethereumOwned, totalValuePortfolio, walletTV;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);

        firestore = FirebaseFirestore.getInstance();
        FirebaseUser logged_user = FirebaseAuth.getInstance().getCurrentUser();
        assert logged_user != null;
        String logged_email = logged_user.getEmail();

        adobeOwned = view.findViewById(R.id.adobeOwned);
        amazonOwned = view.findViewById(R.id.amazonOwned);
        appleOwned = view.findViewById(R.id.appleOwned);
        googleOwned = view.findViewById(R.id.googleOwned);
        microsoftOwned = view.findViewById(R.id.microsoftOwned);
        bitcoinOwned = view.findViewById(R.id.bitcoinOwned);
        ethereumOwned = view.findViewById(R.id.ethereumOwned);
        totalValuePortfolio = view.findViewById(R.id.totalValuePortfolio);
        walletTV = view.findViewById(R.id.walletTV);


        CollectionReference customerWalletsRef = firestore.collection("customer_wallets");
        Query query = customerWalletsRef.whereEqualTo("Email", logged_email);
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // task was successful, now you can iterate over the documents in the query snapshot
                for (DocumentSnapshot doc : task.getResult()) {
                    if (Objects.equals(doc.get("Email"), logged_email)) {
                        // found the matching document
                        userInfo = doc.getData();
                        assert userInfo != null;
                        adobeOwned.setText("$"+ Objects.requireNonNull(userInfo.get("Adobe")).toString());
                        amazonOwned.setText("$"+ Objects.requireNonNull(userInfo.get("Amazon")).toString());
                        appleOwned.setText("$"+ Objects.requireNonNull(userInfo.get("Apple")).toString());
                        googleOwned.setText("$"+ Objects.requireNonNull(userInfo.get("Google")).toString());
                        microsoftOwned.setText("$"+ Objects.requireNonNull(userInfo.get("Microsoft")).toString());
                        bitcoinOwned.setText("$"+ Objects.requireNonNull(userInfo.get("Bitcoin")).toString());
                        ethereumOwned.setText("$"+ Objects.requireNonNull(userInfo.get("Ethereum")).toString());
                        totalValuePortfolio.setText("$"+ Objects.requireNonNull(userInfo.get("Total")).toString());
                        walletTV.setText("$"+ Objects.requireNonNull(userInfo.get("Wallet")).toString());
                    }
                }
            } else {
                // task was not successful, handle the error
                Log.w(TAG, "Error with query");
            }
        });



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}