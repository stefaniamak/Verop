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
    public long insertOrder(Orders orders);

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
    public void updateOrderedItems(OrderedItems orderedItems);

            // Query Updates

    @Query("UPDATE products SET reserve = :reserve WHERE id = :id")
    public void updateProductReserve(int id, int reserve);

        // Queries

            // Users

    @Query("SELECT * FROM users")
    public List<User> getUsers();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    public List<User> getUsers(String username, String password);

    @Query("SELECT * FROM users WHERE id = :id")
    public List<User> getUsersById(int id);

    @Query("SELECT * FROM users WHERE username = :username")
    public List<User> userExists(String username);

            // Products

    @Query("SELECT * FROM products")
    public List<Products> getProducts();

    @Query("SELECT * FROM products WHERE id = :id")
    public Products getProduct(int id);

    @Query("SELECT reserve FROM products WHERE id = :id")
    public Integer getProductReserve(int id);

    @Query("SELECT name FROM products")
    public List<String> getProductsName();

    @Query("SELECT pi.path FROM productImages pi JOIN products pr ON pi.id = pr.imageId WHERE pr.id = :prId")
    public String getImagePath(int prId);

    @Query("SELECT pi.path FROM productImages pi JOIN products pr ON pi.id = pr.imageId WHERE pi.id = :imgId")
    public String getImagePathByImgId(int imgId);

    @Query("SELECT oi.quantity FROM orderedItems oi JOIN products pr ON pr.id = oi.pid WHERE pr.id = :prId")
    public List<Integer> getProductSales(int prId);

    @Query("SELECT title FROM productImages")
    public List<String> getproductImagesTitles();

            // Orders and OrderItems

    @Query("SELECT * FROM orders")
    public List<Orders> getOrders();

    @Query("SELECT * FROM orders WHERE uid = :userId")
    public List<Orders> getUserOrders(int userId);

    @Query("SELECT * FROM orderedItems ord JOIN products p ON ord.pid = p.id WHERE ord.oid = :orderId")
    public List<OrderedItems> getOrderedProductsIds(int orderId);

    @Query("SELECT quantity FROM orderedItems WHERE type = 'purchase'")
    public List<Integer> getTotalPurchases();

    @Query("SELECT quantity FROM orderedItems WHERE type = 'donation'")
    public List<Integer> getTotalDonations();

}
