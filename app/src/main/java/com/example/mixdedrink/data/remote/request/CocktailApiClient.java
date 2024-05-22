package com.example.mixdedrink.data.remote.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mixdedrink.data.models.Cocktail;
import com.example.mixdedrink.data.remote.response.CocktailSearch;
import com.example.mixdedrink.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class CocktailApiClient {

    private final MutableLiveData<List<Cocktail>> mCocktails;
    private RetrieveCocktailsRunnable retrieveCocktailsRunnable;

    /* Singleton */
    private static CocktailApiClient instance;
    public static CocktailApiClient getInstance(){
        if(instance == null) {
            instance = new CocktailApiClient();
        }
        return instance;
    }

    /* Constructor */
    private CocktailApiClient() {
        mCocktails = new MutableLiveData<>();
    }

    /* Getters */
    public LiveData<List<Cocktail>> getCocktails() {
        return mCocktails;
    }

    public void searchCocktailsApi(String query) {

        if(retrieveCocktailsRunnable != null) {
            retrieveCocktailsRunnable = null;
        }
        retrieveCocktailsRunnable = new RetrieveCocktailsRunnable(query);

        final Future<?> myHandler = AppExecutors.getInstance().networkIO().submit(retrieveCocktailsRunnable);

        AppExecutors.getInstance().networkIO().schedule(() -> {
            // canceling retrofit call
            myHandler.cancel(true);
        }, 3000, TimeUnit.MILLISECONDS);
    }



    /* Runnable class: retrieve data from RestAPI */
    private class RetrieveCocktailsRunnable implements Runnable{

        private final String query;
        boolean cancelRequest;

        /* Constructor */
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

                if(response.code() == 200 && response.body()!=null) {
                    List<Cocktail> cocktailList = new ArrayList<>(response.body().getCocktailDtoList());
                    mCocktails.postValue(cocktailList);
                } else {
                    String error = (response.errorBody()!=null) ? response.errorBody().toString() : "";
                    Log.v("Tag", "Error: " + error);
                    mCocktails.postValue(null);
                    cancelRequest();
                }

            } catch (IOException e) {
                mCocktails.postValue(null);
                e.printStackTrace();
                cancelRequest();
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
