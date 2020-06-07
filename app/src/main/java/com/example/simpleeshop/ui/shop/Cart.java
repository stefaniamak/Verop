package com.example.simpleeshop.ui.shop;

import java.util.Hashtable;

public class Cart {
    private Hashtable<Integer,Integer> totalProducts;

    private static Cart instance;
    public static Cart Instance(){
        if(instance == null){
            instance = new Cart();
        }
        return instance;
    }
    public Cart(){
        totalProducts = new Hashtable<>();
    }

    public void AddProduct(int productID){
        if(totalProducts.containsKey(productID))
            totalProducts.put(productID, totalProducts.get(productID) + 1);
        else
            totalProducts.put(productID, 1);
    }

    public void ClearProducts(){
        totalProducts.clear();
    }

    public Hashtable<Integer,Integer> TotalProducts() {
        return totalProducts;
    }
}
