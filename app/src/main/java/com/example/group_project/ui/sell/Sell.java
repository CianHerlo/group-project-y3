package com.example.group_project.ui.sell;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group_project.MainActivity;
import com.example.group_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;
import java.util.Objects;

public class Sell extends AppCompatActivity {

    FirebaseFirestore firestore;
    Map<String, Object> userInfo;
    EditText sellAmountInputQty;
    TextView balanceAmountText, sellTitle, currentPrice, walletFundsSell;
    Button sellBTN;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        sellAmountInputQty = findViewById(R.id.sellAmountInputQty);
        balanceAmountText = findViewById(R.id.balanceAmountText);
        walletFundsSell = findViewById(R.id.walletFundsSell);
        currentPrice = findViewById(R.id.price);
        sellTitle = findViewById(R.id.sellTitle);
        sellBTN = findViewById(R.id.confirmSaleBtn);

        Intent intent = getIntent();
        String trade_name = intent.getStringExtra("Trade_Name");

        firestore = FirebaseFirestore.getInstance();
        FirebaseUser logged_user = FirebaseAuth.getInstance().getCurrentUser();

        CollectionReference customerWalletsRef = firestore.collection("customer_wallets");
        assert logged_user != null;
        Query query = customerWalletsRef.whereEqualTo("Email", logged_user.getEmail());
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // task was successful, now you can iterate over the documents in the query snapshot
                for (DocumentSnapshot doc : task.getResult()) {
                    if (Objects.equals(doc.get("Email"), logged_user.getEmail())) {
                        // found the matching document
                        userInfo = doc.getData();
                        assert userInfo != null;
                        balanceAmountText.setText("$"+ userInfo.get(trade_name));
                        walletFundsSell.setText("$"+ Objects.requireNonNull(userInfo.get("Wallet")));
                    }
                }
            } else {
                // task was not successful, handle the error
                Log.w(TAG, "Error with query");
            }
        });

        sellTitle.setText(trade_name);

        switch (trade_name) {
            case "Adobe":
                currentPrice.setText(R.string.adobe);
                break;
            case "Amazon":
                currentPrice.setText(R.string.amazon);
                break;
            case "Apple":
                currentPrice.setText(R.string.apple);
                break;
            case "Google":
                currentPrice.setText(R.string.google);
                break;
            case "Microsoft":
                currentPrice.setText(R.string.microsoft);
                break;
            case "Bitcoin":
                currentPrice.setText(R.string.bitcoin);
                break;
            case "Ethereum":
                currentPrice.setText(R.string.ethereum);
                break;

            default:
                currentPrice.setText("Price Error!");
                break;
        }
    }

    @Override
    public void onBackPressed() {   // Sends user Back to Home (Main Activity)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}