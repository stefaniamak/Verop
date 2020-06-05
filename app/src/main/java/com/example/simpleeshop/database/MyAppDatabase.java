package com.example.simpleeshop.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.simpleeshop.MyApplication;

import java.io.File;

@Database(entities = {User.class, ProductImages.class, Products.class, Orders.class, OrderedItems.class}, version = 2)


public abstract class MyAppDatabase extends RoomDatabase {

    private static String databaseName = "simpleshopdb";
    // Singleton pattern
    private static MyAppDatabase _instance = null;
    public static MyAppDatabase Instance() {
        if(_instance == null)  // Lazy initialization pattern
           Initialize();

        return _instance;
    }

    private static void Initialize() {

        Context context = MyApplication.Context();

        File dbFile = context.getDatabasePath(databaseName);

        Builder<MyAppDatabase> builder = Room.databaseBuilder(context, MyAppDatabase.class, databaseName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration();

        // Check if the database already exists.
        // If it does not exist, create it from the simpleeshop.db file
        // Comment the next line to reset database
        if(!dbFile.exists())
            builder.createFromAsset("simpleeshop.db");

        _instance = builder.build();
    }


    public abstract MyDao myDao();
}
