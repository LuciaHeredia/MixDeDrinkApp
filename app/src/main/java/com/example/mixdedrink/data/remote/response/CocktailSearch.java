package com.example.mixdedrink.data.remote.response;

import com.example.mixdedrink.data.models.Cocktail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CocktailSearch {

    @SerializedName("drinks")
    @Expose()
    private List<Cocktail> cocktailList;

    public List<Cocktail> getCocktailDtoList() {
        return cocktailList;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "cocktailDtoList=" + cocktailList +
                '}';
    }

}
