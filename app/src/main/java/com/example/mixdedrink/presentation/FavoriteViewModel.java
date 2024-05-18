package com.example.mixdedrink.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mixdedrink.data.local.repositories.FavoriteRepository;
import com.example.mixdedrink.data.models.Cocktail;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private final FavoriteRepository favoriteRepository;
    private final LiveData<List<Cocktail>> allFavorites;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteRepository = new FavoriteRepository(application);
        allFavorites = favoriteRepository.getAllFavorites();
    }

    public void insertFavorite(Cocktail favorite) {
        favoriteRepository.insertFavorite(favorite);
    }

    public void deleteFavorite(Cocktail favorite) {
        favoriteRepository.deleteFavorite(favorite);
    }

    public LiveData<List<Cocktail>> getAllFavorites() {
        return allFavorites;
    }

}
