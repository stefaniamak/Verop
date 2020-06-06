package com.example.simpleeshop.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;



// Data Access Object
// For Database Methods
@Dao
public interface MyDao {

        // Inserts

    @Insert
    public void insertUser(User user);

    @Insert
    public void insertProduct(Products products);

    @Insert
    public void insertOrder(Orders orders);

    @Insert
    public void insertOrderedItems(OrderedItems orderedItems);

        // Deletes

    @Delete
    public void deleteUser(User user);

    @Delete
    public void deleteProduct(Products products);

    @Delete
    public void deleteOrder(Orders orders);

        // Updates

    @Update
    public void updateUser(User user);

    @Update
    public void updateProduct(Products products);

    @Update
    public void updateOrder(Orders orders);

    @Update
    public void updateOrderedItems(OrderedItems orderedItems);

        // Queries

    @Query("SELECT * FROM users")
    public List<User> getUsers();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    public List<User> getUsers(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    public List<User> userExists(String username);

    @Query("SELECT * FROM products")
    public List<Products> getProducts();

    @Query("SELECT name FROM products")
    public List<String> getProductsName();

    @Query("SELECT quantity FROM orderedItems WHERE type = 'purchase'")
    public List<Integer> getTotalPurchases();

    @Query("SELECT quantity FROM orderedItems WHERE type = 'donation'")
    public List<Integer> getTotalDonations();

}
