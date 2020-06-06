package com.example.simpleeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.User;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private sharedPreferenceConfig sharedPreferenceConfig;
    EditText UserName, UserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.fragment_login);

        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        UserName = findViewById(R.id.username);
        UserPassword = findViewById(R.id.password);

        if (sharedPreferenceConfig.readLoginStatus()){
            getIntoTheApp();
        }

   }


   private void  getIntoTheApp(){
       startActivity(new Intent(this, MainActivity.class));
       finish();
   }

    public void signUpPage(View view){
        startActivity(new Intent(this, com.example.simpleeshop.ui.signup.SignUpActivity.class));
        finish();
    }

    public void  loginUser(View view) {
        String varusername = UserName.getText().toString();
        String varuserpassword = UserPassword.getText().toString();

        MyAppDatabase db = MyAppDatabase.Instance();
        List< User > user = db.myDao().getUsers(varusername, varuserpassword);

        if(!user.isEmpty()){
            sharedPreferenceConfig.writeLoginStatus(true);
            getIntoTheApp();
        } else {
            Toast.makeText(this, "Login failed. Incorrect combination.", Toast.LENGTH_SHORT).show();
        }
    }



}

