package com.example.simpleeshop.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "products",
        foreignKeys = {
        @ForeignKey(entity = ProductImages.class,
                parentColumns = "id",
                childColumns = "imageId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)
})

public class Products {
    @PrimaryKey
    private int id;

    @ColumnInfo
    @NonNull
    private String name;

    @ColumnInfo
    @NonNull
    private double price;

    @ColumnInfo
    @NonNull
    private String imageId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    public String getImageId() {
        return imageId;
    }

    public void setImageId(@NonNull String imageId) {
        this.imageId = imageId;
    }
}
