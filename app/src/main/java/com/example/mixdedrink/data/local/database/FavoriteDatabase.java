package com.example.mixdedrink.data.local.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mixdedrink.data.local.daos.FavoriteDao;
import com.example.mixdedrink.data.models.Cocktail;

@Database(entities = {Cocktail.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

    // Singleton
    private static FavoriteDatabase instance;

    // synchronized -> only one thread at a time
    public static synchronized FavoriteDatabase getInstance(Context context) {
        if(instance == null) {
            // creating new app database
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteDatabase.class, "favorites_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
