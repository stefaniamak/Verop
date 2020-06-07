package com.example.simpleeshop.ui.shop;

import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.simpleeshop.R;
import com.example.simpleeshop.database.MyAppDatabase;
import com.example.simpleeshop.database.Products;

import java.util.ArrayList;
import java.util.List;

public class ShopItems extends AppCompatActivity {
    ListView listView;
    ListAdapter adapter;
    List<String> Items = new ArrayList<>();

    // Code below is pretty much useless
//    public ShopItems() {
//
//        getShopItems();
//        listView = listView.findViewById(R.id.shop_list_view);
//        adapter = new ShopListAdapter(this, Items);
//        listView.setAdapter(adapter);
//
//    }
//
//    void getShopItems() {
//        MyAppDatabase db = MyAppDatabase.Instance();
//        List< String > products = db.myDao().getProductsName();
//        for (String item:products){
//            Items.add(item);
//        }
//    }


}
