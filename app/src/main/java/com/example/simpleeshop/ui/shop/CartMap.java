package com.example.simpleeshop.ui.shop;

import android.widget.Toast;

import com.example.simpleeshop.MyApplication;
import com.example.simpleeshop.database.MyAppDatabase;

import java.util.Hashtable;

public class CartMap {
    private Hashtable<Integer,Integer> totalProducts;

    private static CartMap instance;
    public static CartMap Instance(){
        if(instance == null){
            instance = new CartMap();
        }
        return instance;
    }
    public CartMap(){
        totalProducts = new Hashtable<>();
    }

    public void AddProduct(int productID){
        MyAppDatabase db = MyAppDatabase.Instance();
        int productReserve = db.myDao().getProductReserve(productID); // Obtained from Products table
        int productQuantity = 0;

        if (totalProducts.containsKey(productID))
            productQuantity = totalProducts.get(productID); // Obtained from CartMap Map

        if(productQuantity < productReserve){
            if(totalProducts.containsKey(productID))
                totalProducts.put(productID, totalProducts.get(productID) + 1);
            else
                totalProducts.put(productID, 1);
        } else {
            Toast.makeText(MyApplication.Context(), "Not more products in storage.", Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowOrderedProducts(int productID, int quantity){
        totalProducts.put(productID, quantity);
    }

    public void ClearProducts(){
        totalProducts.clear();
    }

    public Hashtable<Integer,Integer> TotalProducts() {
        return totalProducts;
    }
}
