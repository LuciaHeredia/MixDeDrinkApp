package com.example.mixdedrink.data.local.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mixdedrink.data.local.daos.FavoriteDao;
import com.example.mixdedrink.data.local.database.FavoritesDatabase;
import com.example.mixdedrink.data.models.Cocktail;

import java.util.List;

public class FavoriteRepository {
    private FavoriteDao favoriteDao;
    private LiveData<List<Cocktail>> allFavorites;

    public FavoriteRepository(Application application) {
        // db call
        FavoritesDatabase favoritesDatabase = FavoritesDatabase.getInstance(application);
        // db access with dao
        favoriteDao = favoritesDatabase.favoriteDao();
        // db data
        allFavorites = favoriteDao.getAllFavorites();
    }

    public void insertFavorite(Cocktail favorite){
        new InsertFavoriteAsyncTask(favoriteDao).execute(favorite);
    }

    public void updateFavorite(Cocktail favorite){
        new UpdateFavoriteAsyncTask(favoriteDao).execute(favorite);
    }

    public LiveData<List<Cocktail>> getAllFavorites(){
        return allFavorites;
    }


    ////////////////////////// AsyncTasks //////////////////////////

    private static class InsertFavoriteAsyncTask extends AsyncTask<Cocktail, Void, Void> {
        private FavoriteDao favoriteDao;

        private InsertFavoriteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Cocktail... cocktails) {
            favoriteDao.insertFavorite(cocktails[0]);
            return null;
        }
    }

    private static class UpdateFavoriteAsyncTask extends AsyncTask<Cocktail, Void, Void> {
        private FavoriteDao favoriteDao;

        private UpdateFavoriteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Cocktail... cocktails) {
            favoriteDao.updateFavorite(cocktails[0]);
            return null;
        }
    }

}
