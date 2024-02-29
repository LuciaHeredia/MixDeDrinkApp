package com.example.mixdedrink.data.remote.response;

import com.example.mixdedrink.data.remote.dtos.CocktailDto;
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
