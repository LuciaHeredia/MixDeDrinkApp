package com.example.mixdedrink.data.remote.repositories;

import androidx.lifecycle.LiveData;

import com.example.mixdedrink.data.remote.dtos.CocktailDto;
import com.example.mixdedrink.data.remote.request.CocktailApiClient;

import java.util.List;

public class CocktailRepository {

    private static CocktailRepository instance;
    private CocktailApiClient cocktailApiClient;

    // Singleton
    public static CocktailRepository getInstance(){
        if(instance == null) {
            instance = new CocktailRepository();
        }
        return instance;
    }

    private CocktailRepository() {
        cocktailApiClient = CocktailApiClient.getInstance();
    }

    public LiveData<List<CocktailDto>> getCocktails() {
        return cocktailApiClient.getCocktails();
    }

}
