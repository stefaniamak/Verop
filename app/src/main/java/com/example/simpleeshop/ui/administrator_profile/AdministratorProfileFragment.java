package com.example.simpleeshop.ui.administrator_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.simpleeshop.R;
import com.example.simpleeshop.inActivity;

public class AdministratorProfileFragment extends Fragment {

    private AdministratorProfileViewModel administratorProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        administratorProfileViewModel =
                ViewModelProviders.of(this).get(AdministratorProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_administrator_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_account_title);
        administratorProfileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        return root;
    }
}
