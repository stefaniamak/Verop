package com.example.simpleeshop.ui.administrator_profile;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.simpleeshop.ProfileMenu;
import com.example.simpleeshop.R;

public class AccountAction extends AppCompatActivity implements com.example.simpleeshop.ui.profileMenu.ProfileMenu.OnMessageReadListener {
    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_administrator_profile);

        if(findViewById(R.id.profileMenuContainer)!=null){
            if(savedInstanceState!=null){
                return;
            }
        }

        ProfileMenu profileMenu = new ProfileMenu();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.profileMenuContainer, profileMenu, null);
        fragmentTransaction.commit();
    }

    @Override
    public void onMessageRead(String message){
        textView = findViewById(R.id.text_account_title);
        textView.setText(message);
    }
}
