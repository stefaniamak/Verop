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

    EditText UserName, UserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.fragment_login);

        UserName = findViewById(R.id.username);
        UserPassword = findViewById(R.id.password);

        if (MyApplication.Instance().getSharedPreferenceConfig().readLoginStatus()){
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

    public void loginUser(View view) {
        String varusername = UserName.getText().toString();
        String varuserpassword = UserPassword.getText().toString();

        MyAppDatabase db = MyAppDatabase.Instance();
        List< User > user = db.myDao().getUsers(varusername, varuserpassword);

        SharedPreferenceConfig config = MyApplication.Instance().getSharedPreferenceConfig();
        if(!user.isEmpty()){
            config.writeLoginStatus(true);
            int userId =  user.get(0).getId();
            config.writeUserId(userId);
            getIntoTheApp();
        } else {
            Toast.makeText(this, "Login failed. Incorrect combination.", Toast.LENGTH_SHORT).show();
        }
    }



}

