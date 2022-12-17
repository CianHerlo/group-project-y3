package com.example.group_project.ui.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group_project.R;
import com.example.group_project.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {

    private FragmentContactBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentContactBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        Button callBTN = view.findViewById(R.id.callBTN);
        callBTN.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+"0214326100"));
            view1.getContext().startActivity(intent);
        });

        Button emailBTN = view.findViewById(R.id.emailBTN);
        emailBTN.setOnClickListener(view1 -> {
            String emails = "aaron.canty@mycit.ie,a.cullinane-cooney@mycit.ie,cian.herlihy@mycit.ie,roisin.suarez@mycit.ie";
            String [] emailList = emails.split(",");
            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, emailList);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Fintech Trader Mobile Application Support");
            intent.putExtra(Intent.EXTRA_TEXT, "Hi all,\n\nI have an issue with..\n\nKind Regards,\nYour Name.");
            view1.getContext().startActivity(intent);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}