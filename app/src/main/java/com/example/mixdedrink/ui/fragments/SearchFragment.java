package com.example.mixdedrink.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mixdedrink.R;
import com.example.mixdedrink.data.remote.dtos.CocktailDto;
import com.example.mixdedrink.data.remote.Api;
import com.example.mixdedrink.data.remote.request.ServiceRequest;
import com.example.mixdedrink.data.remote.response.CocktailSearch;
import com.example.mixdedrink.databinding.FragmentSearchBinding;
import com.example.mixdedrink.presentation.CocktailListViewModel;
import com.example.mixdedrink.utils.CocktailAdapter;
import com.example.mixdedrink.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private CocktailListViewModel cocktailListViewModel;
    private CocktailAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        disableOnBackBtn();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        cocktailListViewModel = new ViewModelProvider(this).get(CocktailListViewModel.class);
        recyclerViewSetup();
        GetRetrofitResponse();
        listenerSetup();
        return binding.getRoot();
    }

    private void disableOnBackBtn() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button even
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void recyclerViewSetup() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        adapter = new CocktailAdapter();
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::saveRecipeToSharedPref);
    }

    private void saveRecipeToSharedPref(CocktailDto cocktail) {
        goToRecipe();
    }

    private void listenerSetup() {
        //binding.buttonFirst.setOnClickListener(view -> GetRetrofitResponse());

        /*binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hide Toolbar when entering fragment
                ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

                NavHostFragment.findNavController(SearchFragment.this)
                        .navigate(R.id.action_SearchFragment_to_RecipeFragment);
            }
        });*/
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
                    adapter.setCocktails(cocktailDtoList);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.INVISIBLE);
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
            public void onChanged(List<CocktailDto> cocktails) {
                // observing for changes
            }
        });
    }

    private void goToRecipe() {
        NavHostFragment.findNavController(SearchFragment.this)
                .navigate(R.id.action_SearchFragment_to_RecipeFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}