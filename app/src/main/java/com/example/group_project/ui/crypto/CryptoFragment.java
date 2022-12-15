package com.example.group_project.ui.crypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentCryptoBinding;
import com.example.group_project.ui.buy.Buy;
import com.example.group_project.ui.sell.Sell;
import com.google.android.material.snackbar.Snackbar;

public class CryptoFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentCryptoBinding binding;
    private ImageView img, img2;
    private TextView predictionText, predictionText2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCryptoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_crypto, container, false);

        Spinner spinner = view.findViewById(R.id.spinner_crypto);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.cryptos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        img = view.findViewById(R.id.crypto_graph_image);
        img2 = view.findViewById(R.id.crypto_graph_image2);

        img.setImageResource(R.drawable.bitcoin);
        img2.setImageResource(R.drawable.ethereum);

        predictionText = view.findViewById(R.id.predictionText);
        predictionText2 = view.findViewById(R.id.predictionText2);

        Button buyBtn = view.findViewById(R.id.buyBtnCrypto);
        buyBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), Buy.class);
            intent.putExtra("Trade_Name", spinner.getSelectedItem().toString());
            intent.putExtra("Trade_Price", "1234.56");
            intent.putExtra("Owned", "1234.56");
            view1.getContext().startActivity(intent);
        });

        Button sellBtn = view.findViewById(R.id.sellBtnCrypto);
        sellBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), Sell.class);
            intent.putExtra("Trade_Name", spinner.getSelectedItem().toString());
            intent.putExtra("Trade_Price", "1234.56");
            intent.putExtra("Owned", "1234.56");
            view1.getContext().startActivity(intent);
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        String spinner_item = parent.getSelectedItem().toString();

        if (spinner_item.equals("Bitcoin")) {
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);

            predictionText.setVisibility(View.VISIBLE);
            predictionText2.setVisibility(View.INVISIBLE);
        } else {
            img.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.VISIBLE);

            predictionText.setVisibility(View.INVISIBLE);
            predictionText2.setVisibility(View.VISIBLE);
        }


        Snackbar snackBar = Snackbar.make(view.getContext(), view, text, Snackbar.LENGTH_LONG);
        snackBar.setAction("Close", v -> {
            snackBar.dismiss();
        });
        snackBar.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}