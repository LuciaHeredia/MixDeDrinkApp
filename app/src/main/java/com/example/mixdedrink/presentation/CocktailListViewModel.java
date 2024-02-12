package com.example.mixdedrink.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.mixdedrink.data.modelsDto.CocktailDto;
import com.example.mixdedrink.data.repositories.CocktailRepository;

import java.util.List;

public class CocktailListViewModel extends ViewModel {

    private CocktailRepository cocktailRepository;

    // Constructor
    public CocktailListViewModel() {
        cocktailRepository = CocktailRepository.getInstance();
    }

    // Getter
    public LiveData<List<CocktailDto>> getCocktails() {
        return cocktailRepository.getCocktails();
    }

}
