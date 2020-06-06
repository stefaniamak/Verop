package com.example.simpleeshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Products;
import com.example.simpleeshop.database.User;
import com.example.simpleeshop.ui.shop.ShopListAdapter;
import com.example.simpleeshop.ui.signup.SignUpActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private sharedPreferenceConfig sharedPreferenceConfig;
    EditText UserName, UserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.fragment_login);

        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        UserName = findViewById(R.id.username);
        UserPassword = findViewById(R.id.password);
        if (sharedPreferenceConfig.readLoginStatus()){ //sharedPreferenceConfig.readLoginStatus()
            getIntoTheApp();
        }

   }


   private void  getIntoTheApp(){
       startActivity(new Intent(this, inActivity.class));
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

