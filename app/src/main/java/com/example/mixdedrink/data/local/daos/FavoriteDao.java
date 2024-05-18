package com.example.mixdedrink.data.local.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mixdedrink.data.models.Cocktail;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Transaction
    @Insert
    void insertFavorite(Cocktail favorite);

    @Transaction
    @Delete
    void deleteFavorite(Cocktail favorite);

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Cocktail>> getAllFavorites();

}
