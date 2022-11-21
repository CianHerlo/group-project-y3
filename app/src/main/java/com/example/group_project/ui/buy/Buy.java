package com.example.group_project.ui.buy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group_project.MainActivity;
import com.example.group_project.R;

public class Buy extends AppCompatActivity {

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
        String price = intent.getStringExtra("Trade_Price");

        buyTitle.setText(trade_name);
        currentPrice.setText(price);
    }

    @Override
    public void onBackPressed() {   // Sends user Back to Home (Main Activity)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}