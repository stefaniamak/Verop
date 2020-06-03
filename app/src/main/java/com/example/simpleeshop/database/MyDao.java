package com.example.simpleeshop.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao

// Data Access Object
// For Database Methods

public interface MyDao {
    @Insert
    public void addUser(User user);

    @Query("SELECT * FROM users")
    public List<User> getUsers();

    @Query("SELECT * FROM products")
    public List<Products> getProducts();

    @Query("SELECT name FROM products")
    public List<String> getProductsName();

    @Query("SELECT quantity FROM orderedItems WHERE type = 'purchase'")
    public List<Integer> getTotalPurchases();

    @Query("SELECT quantity FROM orderedItems WHERE type = 'donation'")
    public List<Integer> getTotalDonations();
}
