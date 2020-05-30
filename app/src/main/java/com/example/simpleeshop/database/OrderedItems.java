package com.example.simpleeshop.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "orders",
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
    private int oid;

    @ColumnInfo
    private int pid;

    @ColumnInfo
    private int type; // Donation or Purchase

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
