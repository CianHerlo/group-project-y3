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
    TextView balanceAmountText, buyTitle;
    Button buyBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        sellAmountInputQty = findViewById(R.id.sellAmountInputQty);
        balanceAmountText = findViewById(R.id.balanceAmountText);
        buyTitle = findViewById(R.id.buyTitle);
        buyBTN = findViewById(R.id.confirmPurchaseBtn);
    }

    @Override
    public void onBackPressed() {   // Sends user Back to Home (Main Activity)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}