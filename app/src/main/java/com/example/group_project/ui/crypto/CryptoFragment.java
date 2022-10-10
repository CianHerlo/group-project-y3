package com.example.group_project.ui.crypto;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.ViewModelProvider;

        import com.example.group_project.databinding.FragmentCryptoBinding;

public class CryptoFragment extends Fragment {

    private FragmentCryptoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CryptoViewModel cryptoViewModel =
                new ViewModelProvider(this).get(CryptoViewModel.class);

        binding = FragmentCryptoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}