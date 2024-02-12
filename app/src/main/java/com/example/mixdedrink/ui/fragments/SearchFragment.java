package com.example.mixdedrink.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mixdedrink.data.modelsDto.CocktailDto;
import com.example.mixdedrink.data.repositories.remote.Api;
import com.example.mixdedrink.data.repositories.remote.request.ServiceRequest;
import com.example.mixdedrink.data.repositories.remote.respnose.CocktailSearch;
import com.example.mixdedrink.databinding.FragmentSearchBinding;
import com.example.mixdedrink.presentation.CocktailListViewModel;
import com.example.mixdedrink.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;

    // ViewModel
    private CocktailListViewModel cocktailListViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Disable onBack btn pressed
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button even
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);

        cocktailListViewModel = new ViewModelProvider(this).get(CocktailListViewModel.class);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hide Toolbar when entering fragment
                ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

                NavHostFragment.findNavController(SearchFragment.this)
                        .navigate(R.id.action_SearchFragment_to_RecipeFragment);
            }
        });*/

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponse();
            }
        });

    }

    private void GetRetrofitResponse() {
        Api api = ServiceRequest.getApi();

        // cocktails search
        String cocktailName = "";
        Call<CocktailSearch> searchResponseCall = api.searchCocktail(Constants.BASE_URL_SEARCH, cocktailName);

        searchResponseCall.enqueue(new Callback<CocktailSearch>() {
            @Override
            public void onResponse(Call<CocktailSearch> call, Response<CocktailSearch> response) {
                if(response.code() == 200) {
                    Log.v("Tag", "response: " + response.body().toString());
                    List<CocktailDto> cocktailDtoList = new ArrayList<>(response.body().getCocktailDtoList());
                    for(CocktailDto cocktailDto: cocktailDtoList) {
                        Log.v("Tag", "The Glass: " + cocktailDto.getStrDrink());
                    }
                } else {
                    try {
                        Log.v("Tag", "Error: " + response.errorBody().string());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<CocktailSearch> call, Throwable t) {

            }
        });
    }

    private void ObserveAnyChange() {
        cocktailListViewModel.getCocktails().observe(this, new Observer<List<CocktailDto>>() {
            @Override
            public void onChanged(List<CocktailDto> cocktailDtos) {
                // observing for changes
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}