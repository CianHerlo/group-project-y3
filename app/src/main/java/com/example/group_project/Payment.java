package com.example.group_project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    private EditText fundsAddedInput, cardNumInput, cardDateInput, cardCVC;
    private Button paymentBTN;
    FirebaseFirestore firestore;
    Map<String, Object> userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardNumInput = findViewById(R.id.cardNumInput);
        cardDateInput = findViewById(R.id.cardDateInput);
        cardCVC = findViewById(R.id.cardCVC);
        fundsAddedInput = findViewById(R.id.fundsAddedInput);
        paymentBTN = findViewById(R.id.paymentBTN);

        paymentBTN.setOnClickListener(v -> {
            if (fundsAddedInput.getText().toString().length() > 0) {
                if (cardNumInput.getText().length() == 16 && cardDateInput.getText().length() == 5 && cardCVC.getText().length() == 3) {

                    String money = fundsAddedInput.getText().toString();

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String email = auth.getCurrentUser().getEmail();
                    Query query = db.collection("customer_wallets").whereEqualTo("Email", email);

                    query.get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                            DocumentReference docRef = documentSnapshot.getReference();

                            String wallet_old = (String) documentSnapshot.getString("Wallet");
                            Map<String, Object> updates = new HashMap<>();

                            float num1 = Float.parseFloat(wallet_old);
                            float num2 = Float.parseFloat(money);
                            float sum = num1 + num2;
                            String wallet = String.valueOf(sum);

                            updates.put("Wallet", wallet);
                            docRef.update(updates);

                        } else {
                            // Handle error
                            Log.w(TAG, "Error with update");
                        }
                    });

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {   // Sends user Back to Home (Main Activity)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}