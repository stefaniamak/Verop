package com.example.simpleeshop.ui.profileMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simpleeshop.R;
import com.example.simpleeshop.ui.administrator_profile.AdministratorProfileFragment;

public class ProfileMenu extends Fragment {

    private RadioButton radioButtonDetails;
    private RadioButton radioButtonOrders;
    ProfileMenu.OnMessageReadListener onMessageReadListener;

    public interface OnMessageReadListener {
        public void onMessageRead(String message);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_menu, container, false);
        radioButtonDetails = view.findViewById(R.id.radioButton_profile_details);
        radioButtonOrders = view.findViewById(R.id.radioButton_orders);



        // ToDo: Code for ON CLICK

        return view;
    }
}
