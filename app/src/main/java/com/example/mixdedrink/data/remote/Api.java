package com.example.mixdedrink.data.remote;

import com.example.mixdedrink.data.remote.response.CocktailSearch;

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
