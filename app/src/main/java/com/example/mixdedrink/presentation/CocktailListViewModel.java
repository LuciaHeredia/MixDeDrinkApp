package com.example.mixdedrink.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.mixdedrink.data.models.Cocktail;
import com.example.mixdedrink.data.remote.repositories.CocktailRepository;

import java.util.List;

public class CocktailListViewModel extends ViewModel {

    private final CocktailRepository cocktailRepository;

    // Constructor
    public CocktailListViewModel() {
        cocktailRepository = CocktailRepository.getInstance();
    }

    // Getter
    public LiveData<List<Cocktail>> getCocktails() {
        return cocktailRepository.getCocktails();
    }

}
