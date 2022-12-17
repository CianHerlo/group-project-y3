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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

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

                            String wallet_old = (String) Objects.requireNonNull(documentSnapshot.get("Wallet")).toString();
                            Map<String, Object> updates = new HashMap<>();

                            assert wallet_old != null;
                            double num1 = Double.parseDouble(wallet_old);
                            double num2 = Double.parseDouble(money);
                            double sum = num1 + num2;
                            DecimalFormat df = new DecimalFormat("#");
                            String wallet = df.format(sum);

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