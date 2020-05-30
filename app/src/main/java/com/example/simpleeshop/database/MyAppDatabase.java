package com.example.simpleeshop.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.simpleeshop.MyApplication;

@Database(entities = {User.class}, version = 1)


public abstract class MyAppDatabase extends RoomDatabase {

    // Singleton pattern
    private static MyAppDatabase _instance = null;
    public static MyAppDatabase Instance() {
        if(_instance == null) { // Lazy initialization pattern
            _instance = Room.databaseBuilder(MyApplication.Context(), MyAppDatabase.class, "simpleshopdb")
                    .createFromAsset("simpleeshop.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return _instance;
    }

    public abstract MyDao myDao();
}
