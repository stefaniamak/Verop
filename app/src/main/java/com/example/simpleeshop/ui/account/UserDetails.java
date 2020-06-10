package com.example.simpleeshop.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simpleeshop.MainActivity;
import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.SharedPreferenceConfig;
import com.example.simpleeshop.database.User;

import java.util.List;

public class UserDetails extends Fragment implements View.OnClickListener {

    View root;
    private SharedPreferenceConfig sharedPreferenceConfig;
    EditText userName, userPassword;
    String userNameString, userPasswordString;
    int userId;
    Button logoutButton, updateButton, deleteButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user_details, container, false);
        final MainActivity activity = (MainActivity) getActivity();

        logoutButton = root.findViewById(R.id.logoutButton);
        updateButton = root.findViewById(R.id.updateButton);
        deleteButton = root.findViewById(R.id.deleteButton);
        userName = root.findViewById(R.id.username);
        userPassword = root.findViewById(R.id.password);

        disableButtonsIfAdminLogedIn();


        setUsernameToLabel();

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

    private void disableButtonsIfAdminLogedIn(){
        if (MyApplication.Instance().getSharedPreferenceConfig().readUserId() == 1) {
            deleteButton.setVisibility(View.INVISIBLE);
            userName.setVisibility(View.INVISIBLE);
        } else {
            deleteButton.setVisibility(View.VISIBLE);
            userName.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        SharedPreferenceConfig config = MyApplication.Instance().getSharedPreferenceConfig();
        userId = config.readUserId();
        MyAppDatabase db = MyAppDatabase.Instance();
        List< User > users = db.myDao().getUsersById(userId);
        User user = new User();
        user.setId(userId);


        switch (v.getId()){
            case R.id.updateButton:
                userNameString = userName.getText().toString();
                userPasswordString = userPassword.getText().toString();

                if(userNameString.equals("")){
                    user.setUsername(users.get(0).getUsername());
                } else{
                    user.setUsername(userNameString);
                }
                if(userPasswordString.equals("")){
                    user.setPassword(users.get(0).getPassword());
                } else {
                    user.setPassword(userPasswordString);
                }

                db.myDao().updateUser(user);
                Toast.makeText(getActivity(), "DetailsMap updated", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteButton:
                db.myDao().deleteUser(user);
                Toast.makeText(getActivity(), "Account deleted", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).logout();
                break;
        }
    }

    private void setUsernameToLabel(){
        MyAppDatabase db = MyAppDatabase.Instance();
        String userUsername = db.myDao().getUserUsername(MyApplication.Instance().getSharedPreferenceConfig().readUserId());
        TextView userLabel = root.findViewById(R.id.usernameLabel);
        userLabel.setText(userUsername);
    }
}
