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
import com.example.simpleeshop.ui.account.UserOrders;
import com.example.simpleeshop.ui.administrator.OrderListSheetDialog;
import com.example.simpleeshop.ui.administrator.ProductsListSheetDialog;
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
                R.id.nav_home, R.id.nav_shop, R.id.nav_account, R.id.nav_administrator)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        MyAppDatabase db = MyAppDatabase.Instance();


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

    public void openOrderDetailsSheetDialog(UserOrders parent, int orderId){
        UserOrderEditBottomSheetDialog userOrderEditSheet = new UserOrderEditBottomSheetDialog(parent, orderId);
        userOrderEditSheet.show(getSupportFragmentManager(), "Order details Bottom Sheet");
    }

    public void openProductsListSheetDialog(int productId){
        ProductsListSheetDialog productsListSheetDialog = new ProductsListSheetDialog(productId);
        productsListSheetDialog.show(getSupportFragmentManager(), "Order details Bottom Sheet");
    }

    public void openOrderListSheetDialog(){
        OrderListSheetDialog orderListSheetDialog = new OrderListSheetDialog();
        orderListSheetDialog.show(getSupportFragmentManager(), "Order details Bottom Sheet");
    }
}
