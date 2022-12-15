package com.example.group_project.ui.sell;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group_project.MainActivity;
import com.example.group_project.R;

public class Sell extends AppCompatActivity {

    EditText sellAmountInputQty;
    TextView balanceAmountText, sellTitle, currentPrice;
    Button sellBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        sellAmountInputQty = findViewById(R.id.sellAmountInputQty);
        balanceAmountText = findViewById(R.id.balanceAmountText);
        currentPrice = findViewById(R.id.price);
        sellTitle = findViewById(R.id.sellTitle);
        sellBTN = findViewById(R.id.confirmSaleBtn);

        Intent intent = getIntent();
        String trade_name = intent.getStringExtra("Trade_Name");
        String owned = intent.getStringExtra("Owned");

        sellTitle.setText(trade_name);
        balanceAmountText.setText(owned);

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