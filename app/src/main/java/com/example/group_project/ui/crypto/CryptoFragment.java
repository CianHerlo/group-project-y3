package com.example.group_project.ui.crypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentCryptoBinding;
import com.example.group_project.ui.buy.Buy;

public class CryptoFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentCryptoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CryptoViewModel cryptoViewModel = new ViewModelProvider(this).get(CryptoViewModel.class);

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

        Button buyBtn = view.findViewById(R.id.buyBtnCrypto);
        buyBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), Buy.class);
//               intent.putExtra("Stock_Name", spinnerStocks.getText());
//               intent.putExtra("Stock_Price", );
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
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}