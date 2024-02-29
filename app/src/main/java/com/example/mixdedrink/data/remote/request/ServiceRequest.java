package com.example.mixdedrink.data.remote.request;

import com.example.mixdedrink.data.remote.Api;
import com.example.mixdedrink.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRequest {

    // Service Generator
    private static final Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    // Singleton Retrofit
    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final Api api = retrofit.create(Api.class);

    public static Api getApi() {
        return api;
    }
}
