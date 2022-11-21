package com.example.group_project.ui.stocks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentStocksBinding;
import com.example.group_project.ui.buy.Buy;
import com.google.android.material.snackbar.Snackbar;

public class StocksFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentStocksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        StocksViewModel stocksViewModel = new ViewModelProvider(this).get(StocksViewModel.class);
        binding = FragmentStocksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);

       super.onViewCreated(view, savedInstanceState);

        Spinner spinner = view.findViewById(R.id.spinner_stocks);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.stocks, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button buyBtn = view.findViewById(R.id.buyBtn);
        buyBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), Buy.class);
//               intent.putExtra("Stock_Name", spinnerStocks.getText());
//               intent.putExtra("Stock_Price", );
             view1.getContext().startActivity(intent);
        });

        return view;
        //return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        Snackbar snackBar = Snackbar.make(view.getContext(), view, text, Snackbar.LENGTH_LONG);
        snackBar.setAction("Close", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your action method here
                snackBar.dismiss();
            }
        });
        snackBar.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}