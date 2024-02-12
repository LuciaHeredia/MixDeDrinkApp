package com.example.mixdedrink.data.repositories.remote.respnose;

import com.example.mixdedrink.data.modelsDto.CocktailDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CocktailSearch {

    @SerializedName("drinks")
    @Expose()
    private List<CocktailDto> cocktailDtoList;

    public List<CocktailDto> getCocktailDtoList() {
        return cocktailDtoList;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "cocktailDtoList=" + cocktailDtoList +
                '}';
    }

}
