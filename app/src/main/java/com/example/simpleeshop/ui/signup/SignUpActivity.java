package com.example.simpleeshop.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simpleeshop.MainActivity;
import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.User;
import com.example.simpleeshop.sharedPreferenceConfig;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    EditText UserName, UserPassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);
    }

    public void loginPage(View view){
        openLoginPage();
    }

    public void openLoginPage(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void  signUp(View view) {
        UserName = findViewById(R.id.username);
        UserPassword = findViewById(R.id.password);
        String varusername = UserName.getText().toString();
        String varuserpassword = UserPassword.getText().toString();


        MyAppDatabase db = MyAppDatabase.Instance();
        List< User > userExists = db.myDao().userExists(varusername);

        if(userExists.isEmpty()){
            User user = new User();
            List< User > users = db.myDao().getUsers();
            int id = users.size()+1;
            user.setId(id);
            user.setUsername(varusername);
            user.setPassword(varuserpassword);

            db.myDao().insertUser(user);

            Toast.makeText(this, "New account created successfully.", Toast.LENGTH_SHORT).show();
            UserName.setText("");
            UserPassword.setText("");
            openLoginPage();
        } else {
            Toast.makeText(this, "Username already exists.", Toast.LENGTH_SHORT).show();
        }


    }
}
