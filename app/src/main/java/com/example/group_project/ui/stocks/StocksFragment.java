package com.example.group_project.ui.stocks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentStocksBinding;
import com.example.group_project.ui.buy.BuyFragment;

public class StocksFragment extends Fragment {

    private FragmentStocksBinding binding;
    private Button buyBtn;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        StocksViewModel stocksViewModel = new ViewModelProvider(this).get(StocksViewModel.class);
        binding = FragmentStocksBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);

       super.onViewCreated(view, savedInstanceState);

       buyBtn = view.findViewById(R.id.buyBtn);
       buyBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Fragment buyFrag = new BuyFragment();
//               ConstraintLayout containerStocks = view.findViewById(R.id.containerStocks);
//               containerStocks.removeAllViews();
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.containerStocks, buyFrag);
               fragmentTransaction.addToBackStack(null);
               fragmentTransaction.commit();
           }
       });

        return view;
        //return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}