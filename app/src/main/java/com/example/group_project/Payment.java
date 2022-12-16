package com.example.group_project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    private EditText fundsAddedInput; // cardNumInput, cardDateInput, cardCVC
    private Button paymentBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

//        cardNumInput = findViewById(R.id.cardNumInput);
//        cardDateInput = findViewById(R.id.cardDateInput);
//        cardCVC = findViewById(R.id.cardCVC);
        fundsAddedInput = findViewById(R.id.fundsAddedInput);
        paymentBTN = findViewById(R.id.paymentBTN);

        paymentBTN.setOnClickListener(v -> {
            if (fundsAddedInput.getText().toString().length() > 1) {
                String money = fundsAddedInput.getText().toString();
            }
        });

    }
}