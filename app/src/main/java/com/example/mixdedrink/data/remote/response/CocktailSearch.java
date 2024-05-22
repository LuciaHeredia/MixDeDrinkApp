package com.example.mixdedrink.data.remote.response;

import androidx.annotation.NonNull;

import com.example.mixdedrink.data.models.Cocktail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CocktailSearch {

    @SerializedName("drinks")
    @Expose()
    private final List<Cocktail> cocktailList;

    /* Constructor */
    public CocktailSearch(List<Cocktail> cocktailList) {
        this.cocktailList = new ArrayList<>(cocktailList);
    }

    /* Getter */
    public List<Cocktail> getCocktailDtoList() {
        return cocktailList;
    }

    @NonNull
    @Override
    public String toString() {
        return "SearchResponse{" +
                "cocktailDtoList=" + cocktailList +
                '}';
    }

}
