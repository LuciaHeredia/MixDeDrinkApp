package com.example.mixdedrink.data.remote.repositories;

import androidx.lifecycle.LiveData;

import com.example.mixdedrink.data.models.Cocktail;
import com.example.mixdedrink.data.remote.request.CocktailApiClient;

import java.util.List;

public class CocktailRepository {

    private static CocktailRepository instance;
    private final CocktailApiClient cocktailApiClient;

    // Singleton
    public static CocktailRepository getInstance(){
        if(instance == null) {
            instance = new CocktailRepository();
        }
        return instance;
    }

    /* Constructor */
    private CocktailRepository() {
        cocktailApiClient = CocktailApiClient.getInstance();
    }

    public LiveData<List<Cocktail>> getCocktails() {
        return cocktailApiClient.getCocktails();
    }

    public void searchCocktailsApi(String query) {
        cocktailApiClient.searchCocktailsApi(query);
    }

}
