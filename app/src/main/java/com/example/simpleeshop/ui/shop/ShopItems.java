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
    
}
