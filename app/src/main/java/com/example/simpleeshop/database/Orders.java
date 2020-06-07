package com.example.simpleeshop.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "orders",
        foreignKeys = {
        @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "uid",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
        }
)

public class Orders {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo
    @NonNull
    private int uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
