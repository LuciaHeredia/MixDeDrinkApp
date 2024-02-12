package com.example.mixdedrink.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.mixdedrink.data.modelsDto.CocktailDto;
import com.example.mixdedrink.data.repositories.remote.request.CocktailApiClient;

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
