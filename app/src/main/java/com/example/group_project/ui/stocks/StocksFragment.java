package com.example.group_project.ui.stocks;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentStocksBinding;
import com.example.group_project.ui.buy.Buy;
import com.example.group_project.ui.sell.Sell;
import com.google.android.material.snackbar.Snackbar;

public class StocksFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentStocksBinding binding;
    private ImageView img, img2, img3, img4, img5;

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
        img = view.findViewById(R.id.stock_graph_image);
        img2 = view.findViewById(R.id.stock_graph_image2);
        img3 = view.findViewById(R.id.stock_graph_image3);
        img4 = view.findViewById(R.id.stock_graph_image4);
        img5 = view.findViewById(R.id.stock_graph_image5);
        img.setImageResource(R.drawable.adobe);
        img2.setImageResource(R.drawable.amazon);
        img3.setImageResource(R.drawable.apple);
        img4.setImageResource(R.drawable.google);
        img5.setImageResource(R.drawable.microsoft);

        Button buyBtn = view.findViewById(R.id.buyBtn);
        buyBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), Buy.class);
            intent.putExtra("Trade_Name", spinner.getSelectedItem().toString());
            intent.putExtra("Trade_Price", "1234.56");
            intent.putExtra("Owned", "1234.56");
             view1.getContext().startActivity(intent);
        });

        Button sellBtn = view.findViewById(R.id.sellBtn);
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
        if (spinner_item.equals("Adobe")) {
            img.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.INVISIBLE);
            img5.setVisibility(View.INVISIBLE);
        }
        else if (spinner_item.equals("Amazon")) {
            img.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.VISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.INVISIBLE);
            img5.setVisibility(View.INVISIBLE);
        }
        else if (spinner_item.equals("Apple")) {
            img.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.VISIBLE);
            img4.setVisibility(View.INVISIBLE);
            img5.setVisibility(View.INVISIBLE);
        }
        else if (spinner_item.equals("Google")) {
            img.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.VISIBLE);
            img5.setVisibility(View.INVISIBLE);
        } else {
            img.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.INVISIBLE);
            img5.setVisibility(View.VISIBLE);
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