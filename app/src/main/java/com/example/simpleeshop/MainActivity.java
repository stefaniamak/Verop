package com.example.simpleeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.ui.account.UserOrderEditBottomSheetDialog;
import com.example.simpleeshop.ui.shop.CartBottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferenceConfig sharedPreferenceConfig;
    private AppBarConfiguration mAppBarConfiguration;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enterApp();



        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

//        if(findViewById(R.id.profileMenuContainer)!=null){
//            if(savedInstanceState!=null){
//                return;
//            }
//        }
//
//        ProfileMenu profileMenu = new ProfileMenu();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.profileMenuContainer, profileMenu, null);
//        fragmentTransaction.commit();
    }

    public void enterApp(){
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartBottomSheetDialog cartBottomSheet = new CartBottomSheetDialog();
                cartBottomSheet.show(getSupportFragmentManager(), "CartFragment Bottom Sheet");

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_shop, R.id.nav_administrator_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //startActivity(new Intent(this, AccountAction.class));

        //setContentView(R.layout.fragment_account);



        //navigationView.addView(R.layout.fragment_login);

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                return true;
//            }
//        });

//        setContentView(R.layout.fragment_shop);

        MyAppDatabase db = MyAppDatabase.Instance();

        //List<User> users = db.myDao().getUsers();
        //List<Products> products = db.myDao().getProducts();
        //List<User> orderedItems = db.myDao().getUsers();
        //List<Products> orders = db.myDao().getProducts();
        //List<User> productImages = db.myDao().getUsers();



    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void logout() {
        sharedPreferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void openOrderDetailsSheetDialog(View v){
        UserOrderEditBottomSheetDialog userOrderEditSheet = new UserOrderEditBottomSheetDialog();
        userOrderEditSheet.show(getSupportFragmentManager(), "Order details Bottom Sheet");
    }
}
