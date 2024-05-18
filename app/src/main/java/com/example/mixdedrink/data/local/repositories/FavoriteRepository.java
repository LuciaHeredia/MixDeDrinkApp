package com.example.mixdedrink.data.local.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mixdedrink.data.local.daos.FavoriteDao;
import com.example.mixdedrink.data.local.database.FavoriteDatabase;
import com.example.mixdedrink.data.models.Cocktail;

import java.util.List;

public class FavoriteRepository {
    private FavoriteDao favoriteDao;
    private LiveData<List<Cocktail>> allFavorites;

    public FavoriteRepository(Application application) {
        // db call
        FavoriteDatabase favoriteDatabase = FavoriteDatabase.getInstance(application);
        // db access with dao
        favoriteDao = favoriteDatabase.favoriteDao();
        // db data
        allFavorites = favoriteDao.getAllFavorites();
    }

    public void insertFavorite(Cocktail favorite){
        new InsertFavoriteAsyncTask(favoriteDao).execute(favorite);
    }

    public void deleteFavorite(Cocktail favorite){
        new DeleteFavoriteAsyncTask(favoriteDao).execute(favorite);
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

    private static class DeleteFavoriteAsyncTask extends AsyncTask<Cocktail, Void, Void> {
        private FavoriteDao favoriteDao;

        private DeleteFavoriteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Cocktail... cocktails) {
            favoriteDao.deleteFavorite(cocktails[0]);
            return null;
        }
    }

}
