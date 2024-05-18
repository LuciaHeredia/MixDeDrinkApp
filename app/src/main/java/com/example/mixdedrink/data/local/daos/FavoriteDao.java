package com.example.mixdedrink.data.local.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mixdedrink.data.models.Cocktail;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Transaction
    @Insert
    void insertFavorite(Cocktail favorite);

    @Transaction
    @Update
    void updateFavorite(Cocktail favorite);

    @Query("SELECT * FROM favorites_table")
    LiveData<List<Cocktail>> getAllFavorites();

}
