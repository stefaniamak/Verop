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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private sharedPreferenceConfig sharedPreferenceConfig;
    EditText UserName, UserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_login);

//        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_loginFragment).build();
//        setContentView(R.id.start_navigation);
//        startActivityFromFragment(R.id);
//        NavController navController = Navigation.findNavController(this, R.id.nav_loginFragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        UserName = findViewById(R.id.username);
        UserPassword = findViewById(R.id.password);
        if (sharedPreferenceConfig.readLoginStatus()){ //sharedPreferenceConfig.readLoginStatus()
            getIntoTheApp();
        }




        //enterApp();
   }


   private void  getIntoTheApp(){
       startActivity(new Intent(this, inActivity.class));
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
//

    }



//
//   public void enterApp(){
//       setContentView(R.layout.activity_main);
//       Toolbar toolbar = findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);
//       FloatingActionButton fab = findViewById(R.id.fab);
//       fab.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                       .setAction("Action", null).show();
//           }
//       });
//       DrawerLayout drawer = findViewById(R.id.drawer_layout);
//       NavigationView navigationView = findViewById(R.id.nav_view);
//       // Passing each menu ID as a set of Ids because each
//       // menu should be considered as top level destinations.
//       mAppBarConfiguration = new AppBarConfiguration.Builder(
//               R.id.nav_home, R.id.nav_shop, R.id.nav_administrator_profile, R.id.nav_loginFragment, R.id.nav_signupFragment)
//               .setDrawerLayout(drawer)
//               .build();
//       NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//       NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//       NavigationUI.setupWithNavController(navigationView, navController);
//
//       //navigationView.addView(R.layout.fragment_login);
//
////        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                return true;
////            }
////        });
//
////        setContentView(R.layout.fragment_shop);
//
//       MyAppDatabase db = MyAppDatabase.Instance();
//
//       List<User> users = db.myDao().getUsers();
//       List<Products> products = db.myDao().getProducts();
//       //List<User> orderedItems = db.myDao().getUsers();
//       //List<Products> orders = db.myDao().getProducts();
//       //List<User> productImages = db.myDao().getUsers();
//   }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_login: {
//                Toast.makeText(this, "LogIn", Toast.LENGTH_LONG).show();
//                setContentView(R.layout.fragment_login);
//
//                //addContentView(R.layout.fragment_login);
//
//                return true;
//            }
//            case R.id.action_signup: {
//                Toast.makeText(this, "SignUp", Toast.LENGTH_LONG).show();
//                setContentView(R.layout.fragment_signup);
//                return true;
//            }
//            case R.id.action_logout: {
//                Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show();
//                setContentView(R.layout.activity_main);
//                return true;
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


}

