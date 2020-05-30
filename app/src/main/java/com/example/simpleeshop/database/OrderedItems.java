package com.example.simpleeshop.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "orderedItems",
        primaryKeys = {"oid", "pid", "type"},
        foreignKeys = {
        @ForeignKey(entity = Orders.class,
                parentColumns = "id",
                childColumns = "oid",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Products.class,
                parentColumns = "id",
                childColumns = "pid",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)
        }
)

public class OrderedItems {
    @ColumnInfo
    @NonNull
    private int oid;

    @ColumnInfo
    @NonNull
    private int pid;

    @ColumnInfo
    @NonNull
    private String type; // Donation or Purchase

    @ColumnInfo
    @NonNull
    private int quantity;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
