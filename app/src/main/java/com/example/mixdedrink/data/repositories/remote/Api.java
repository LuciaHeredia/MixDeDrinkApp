package com.example.mixdedrink.data.repositories.remote;

import com.example.mixdedrink.data.repositories.remote.respnose.CocktailSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    // search cocktails
    @GET("{search}")
    Call<CocktailSearch> searchCocktail(
            @Path("search") String baseUrlSearch,
            @Query("s") String strDrink
    );

}
