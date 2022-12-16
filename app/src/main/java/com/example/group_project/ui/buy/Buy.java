package com.example.group_project.ui.buy;

import static android.content.ContentValues.TAG;

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

public class Buy extends AppCompatActivity {

    FirebaseFirestore firestore;
    Map<String, Object> userInfo;
    EditText sellAmountInputQty;
    TextView balanceAmountText, buyTitle, currentPrice;
    Button buyBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        sellAmountInputQty = findViewById(R.id.sellAmountInputQty);
        balanceAmountText = findViewById(R.id.balanceAmountText);
        currentPrice = findViewById(R.id.price);
        buyTitle = findViewById(R.id.buyTitle);
        buyBTN = findViewById(R.id.confirmPurchaseBtn);

        Intent intent = getIntent();
        String trade_name = intent.getStringExtra("Trade_Name");
        String owned = intent.getStringExtra("Owned");

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
                        balanceAmountText.setText("$"+(String) userInfo.get(trade_name));
                    }
                }
            } else {
                // task was not successful, handle the error
                Log.w(TAG, "Error with query");
            }
        });

        buyTitle.setText(trade_name);

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