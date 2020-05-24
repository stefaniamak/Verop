package com.example.simpleeshop.ui.shop;

import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.simpleeshop.R;

import java.util.ArrayList;
import java.util.List;

public class ShopItems extends AppCompatActivity {
    ListView listView;
    ListAdapter adapter;
    List<String> Items = new ArrayList<>();

    public ShopItems() {

        getShopItems();
        listView = listView.findViewById(R.id.shop_list_view);
        adapter = new ShopListAdapter(this, Items);
        listView.setAdapter(adapter);

    }

    void getShopItems() {
        String[] items = getResources().getStringArray(R.array.item_test_list);
        for (String item:items){
            Items.add(item);
        }
    }
}
