package com.example.mixdedrink.data.repositories.remote.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mixdedrink.data.modelsDto.CocktailDto;
import com.example.mixdedrink.data.repositories.remote.respnose.CocktailSearch;
import com.example.mixdedrink.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class CocktailApiClient {

    //LiveData
    private MutableLiveData<List<CocktailDto>> mCocktails;

    private RetrieveCocktailsRunnable retrieveCocktailsRunnable;

    // Singleton
    private static CocktailApiClient instance;
    public static CocktailApiClient getInstance(){
        if(instance == null) {
            instance = new CocktailApiClient();
        }
        return instance;
    }

    private CocktailApiClient() {
        mCocktails = new MutableLiveData();
    }

    public LiveData<List<CocktailDto>> getCocktails() {
        return mCocktails;
    }

    public void searchCocktailsApi(String query) {

        if(retrieveCocktailsRunnable != null) {
            retrieveCocktailsRunnable = null;
        }
        retrieveCocktailsRunnable = new RetrieveCocktailsRunnable(query);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveCocktailsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // canceling retrofit call after 5 seconds
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MICROSECONDS);
    }

    // retrieve data from RestAPI by runnable class
    private class RetrieveCocktailsRunnable implements Runnable{

        private String query;
        boolean cancelRequest;

        public RetrieveCocktailsRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {
            // getting response objects
            try{
                Response<CocktailSearch> response = getCocktails(query).execute();
                if (cancelRequest) {
                    return;
                }

                if(response.code() == 200) {
                    List<CocktailDto> cocktailDtoList = new ArrayList<>(((CocktailSearch)response.body()).getCocktailDtoList());
                    mCocktails.postValue(cocktailDtoList);
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    mCocktails.postValue(null);
                }

            } catch (IOException e) {
                mCocktails.postValue(null);
                e.printStackTrace();
            }

        }

        private Call<CocktailSearch> getCocktails(String query) {
                return ServiceRequest.getApi().searchCocktail(
                        Constants.BASE_URL_SEARCH,
                        query);
        }

        private void cancelRequest() {
            cancelRequest = true;
        }

    }

}
