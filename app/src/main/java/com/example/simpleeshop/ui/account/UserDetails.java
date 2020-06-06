package com.example.simpleeshop.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simpleeshop.MainActivity;
import com.example.simpleeshop.R;
import com.example.simpleeshop.sharedPreferenceConfig;

public class UserDetails extends Fragment implements View.OnClickListener {

    private com.example.simpleeshop.sharedPreferenceConfig sharedPreferenceConfig;
    EditText UserName, UserPassword;
    int userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_details, container, false);
        final MainActivity activity = (MainActivity) getActivity();

        Button logoutButton = root.findViewById(R.id.logoutButton);
        Button updateButton = root.findViewById(R.id.updateButton);
        Button deleteButton = root.findViewById(R.id.deleteButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.logout();
            }
        });
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v) {

//        UserName = findViewById(R.id.username);
//        UserPassword = findViewById(R.id.password);

        userId = sharedPreferenceConfig.readUserId();

        switch (v.getId()){
            case R.id.updateButton:
                // todo: update
                break;
            case R.id.deleteButton:
                // todo: delete
                break;
        }
    }
}
