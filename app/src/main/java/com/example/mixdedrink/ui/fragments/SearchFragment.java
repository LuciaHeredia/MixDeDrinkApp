package com.example.mixdedrink.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private CocktailListViewModel cocktailListViewModel;
    private List<CocktailDto> allCocktails = new ArrayList<>();
    private List<CocktailDto> filteredCocktails = new ArrayList<>();
    private CocktailAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        disableOnBackBtn();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        cocktailListViewModel = new ViewModelProvider(this).get(CocktailListViewModel.class);
        dropDownSetUp();
        recyclerViewSetup();
        dataSetup();
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

    private void dropDownSetUp() {
        String[] s = getResources().getStringArray(R.array.search_by);
        binding.dropDownInput.setText(s[0]); // Default: Cocktail
    }

    private void recyclerViewSetup() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        adapter = new CocktailAdapter();
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::saveRecipeToSharedPref);
    }

    private void dataSetup() {
        if(filteredCocktails.isEmpty()) {
            GetRetrofitResponse();
        } else {
            adapter.setCocktails(filteredCocktails);
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
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

        /* Search View */
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String onTextChange) {
                String dropDownSelected = binding.dropDownInput.getText().toString();
                if(onTextChange.isEmpty()) {
                    adapter.setCocktails(allCocktails);
                } else {
                    filterCocktails(dropDownSelected, onTextChange);
                }
                return false;
            }

        });

        /* Floating Icon - Random Cocktail */
        binding.randomCocktail.setOnClickListener(view -> {
            Snackbar.make(view, "Get Random Cocktail", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

    }

    private void filterCocktails(String dropDownSelected, String str) {
        filteredCocktails.clear();
        for(CocktailDto cocktailDto: allCocktails) {
            if(dropDownSelected.equals("Cocktail")) {
                if(cocktailDto.getStrDrink().toLowerCase().contains(str.toLowerCase())) {
                    filteredCocktails.add(cocktailDto);
                }
            } else {
                if(cocktailDto.getIsIngredientInside(str)) {
                    filteredCocktails.add(cocktailDto);
                }
            }
        }
        adapter.setCocktails(filteredCocktails);
    }

    private void GetRetrofitResponse() {
        Api api = ServiceRequest.getApi();

        // cocktails search
        String cocktailName = "";
        Call<CocktailSearch> searchResponseCall = api.searchCocktail(Constants.BASE_URL_SEARCH, cocktailName);

        searchResponseCall.enqueue(new Callback<CocktailSearch>() {
            @Override
            public void onResponse(@NonNull Call<CocktailSearch> call, @NonNull Response<CocktailSearch> response) {
                if(response.code() == 200 && response.body()!=null) {
                    allCocktails = response.body().getCocktailDtoList();
                    adapter.setCocktails(allCocktails);
                    //binding.recyclerView.setVisibility(View.VISIBLE);
                    //binding.progressBar.setVisibility(View.INVISIBLE);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        /* current icon tapped: Search */
        MenuItem item = menu.findItem(R.id.searchFragment);
        if (item != null) {
            item.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_search_tapped, null));
            item.setEnabled(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}