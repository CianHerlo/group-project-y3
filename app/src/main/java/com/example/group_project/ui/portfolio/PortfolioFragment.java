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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
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


        DecimalFormat df = new DecimalFormat("#.00");
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
                        String adobe = df.format(userInfo.get("Adobe"));
                        String amazon = df.format(userInfo.get("Amazon"));
                        String apple = df.format(userInfo.get("Apple"));
                        String google = df.format(userInfo.get("Google"));
                        String microsoft = df.format(userInfo.get("Microsoft"));
                        String bitcoin = df.format(userInfo.get("Bitcoin"));
                        String ethereum = df.format(userInfo.get("Ethereum"));

                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
                        String wallet_string = (String) Objects.requireNonNull(userInfo.get("Wallet")).toString();
                        String total_string = Objects.requireNonNull(userInfo.get("Total")).toString();
                        double wallet_double = Double.parseDouble(wallet_string);
                        double total_double = Double.parseDouble(total_string);
                        String wallet_formatted = currencyFormat.format(wallet_double);
                        String total_formatted = currencyFormat.format(total_double);

                        adobeOwned.setText(""+ adobe);
                        amazonOwned.setText(""+ amazon);
                        appleOwned.setText(""+ apple);
                        googleOwned.setText(""+ google);
                        microsoftOwned.setText(""+ microsoft);
                        bitcoinOwned.setText(""+ bitcoin);
                        ethereumOwned.setText(""+ ethereum);
                        totalValuePortfolio.setText(""+ total_formatted);
                        walletTV.setText(""+ wallet_formatted);
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