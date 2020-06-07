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
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)
})
// ON UPDATE CASCADE ON DELETE CASCADE
public class Products {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo
    @NonNull
    private String name;

    @ColumnInfo
    @NonNull
    private double price;

    @ColumnInfo
    @NonNull
    private int imageId;

    @ColumnInfo
    @NonNull
    private int reserve;

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

    public int getReserve() {
        return reserve;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    public int getImageId() {
        return imageId;
    }

    public void setImageId(@NonNull int imageId) {
        this.imageId = imageId;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }
}
