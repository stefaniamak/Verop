package com.example.simpleeshop.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simpleeshop.MainActivity;
import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.SharedPreferenceConfig;

public class UserDetails extends Fragment implements View.OnClickListener {

    private SharedPreferenceConfig sharedPreferenceConfig;
    EditText UserName, UserPassword;
    int userId;
    Button logoutButton, updateButton, deleteButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_details, container, false);
        final MainActivity activity = (MainActivity) getActivity();

        logoutButton = root.findViewById(R.id.logoutButton);
        updateButton = root.findViewById(R.id.updateButton);
        deleteButton = root.findViewById(R.id.deleteButton);

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

        SharedPreferenceConfig config = MyApplication.Instance().getSharedPreferenceConfig();
        userId = config.readUserId();
        MyAppDatabase db = MyAppDatabase.Instance();

        switch (v.getId()){
            case R.id.updateButton:
                // todo: update
                Toast.makeText(getActivity(), "Details updated", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteButton:
//                User user = new User();
//                user.setId(userId);
//                db.myDao().deleteUser(user);
                Toast.makeText(getActivity(), "Account deleted", Toast.LENGTH_SHORT).show();
//                ((MainActivity)getActivity()).logout();
                break;
        }
    }
}
