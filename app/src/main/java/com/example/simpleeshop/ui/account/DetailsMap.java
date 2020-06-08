package com.example.simpleeshop.ui.account;

import com.example.simpleeshop.database.MyAppDatabase;

import java.util.Hashtable;

public class DetailsMap {
    private Hashtable<Integer,Integer> totalProducts;

    private static DetailsMap instance;
    public static DetailsMap Instance(){
        if(instance == null){
            instance = new DetailsMap();
        }
        return instance;
    }
    public DetailsMap(){
        totalProducts = new Hashtable<>();
    }

    public void AddProduct(int productID){
        MyAppDatabase db = MyAppDatabase.Instance();
        int productReserve = db.myDao().getProductReserve(productID); // Obtained from Products table

        int productQuantity = 0; // Obtained from CartMap Map

        if(productQuantity <= productReserve){
            if(totalProducts.containsKey(productID))
                totalProducts.put(productID, totalProducts.get(productID) + 1);
            else
                totalProducts.put(productID, 1);
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
