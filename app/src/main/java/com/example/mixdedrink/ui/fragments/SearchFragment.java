package com.example.mixdedrink.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mixdedrink.R;
import com.example.mixdedrink.data.models.Cocktail;
import com.example.mixdedrink.databinding.FragmentSearchBinding;
import com.example.mixdedrink.presentation.CocktailListViewModel;
import com.example.mixdedrink.utils.CocktailAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private CocktailListViewModel cocktailListViewModel;
    private List<Cocktail> allCocktails = new ArrayList<>();
    private List<Cocktail> filteredCocktails = new ArrayList<>();
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
        apiObserverSetup();
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
        String[] ddItems = getResources().getStringArray(R.array.search_by);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.list_item, ddItems);
        binding.dropDownInput.setAdapter(arrayAdapter);
        //binding.dropDownInput.setText(ddItems[0]); // Default: Cocktail
    }

    private void recyclerViewSetup() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        adapter = new CocktailAdapter();
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::sendCocktailData);
    }

    private void dataSetup() {
        if(filteredCocktails.isEmpty()) {
            getCocktailsFromApi();
        } else {
            adapter.setCocktails(filteredCocktails);
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void sendCocktailData(Cocktail cocktail) {
        goToRecipe(cocktail);
    }

    private void listenerSetup() {

        /* Search View */
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String dropDownSelected = binding.dropDownInput.getText().toString();
                if(binding.dropDownInput.getText().toString().isEmpty()) {
                    binding.dropDown.setErrorEnabled(true);
                    binding.dropDown.setError("select from list");
                } else {
                    binding.dropDown.setErrorEnabled(false);
                    filterCocktails(dropDownSelected, query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String onTextChange) {
                String dropDownSelected = binding.dropDownInput.getText().toString();
                if(onTextChange.isEmpty()) {
                    adapter.setCocktails(allCocktails);
                } else {
                    if(binding.dropDownInput.getText().toString().isEmpty()) {
                        binding.dropDown.setErrorEnabled(true);
                        binding.dropDown.setError("select from list");
                    } else {
                        binding.dropDown.setErrorEnabled(false);
                        filterCocktails(dropDownSelected, onTextChange);
                    }
                }
                return false;
            }

        });

        /* Floating Icon - Random Cocktail */
        binding.randomCocktail.setOnClickListener(this::getRandomCocktail);

    }

    private void getRandomCocktail(View view) {
        int upperbound = allCocktails.size();
        if(upperbound>0) { // Cocktails list exist
            Snackbar.make(view, "Random Cocktail", Snackbar.ANIMATION_MODE_SLIDE)
                    .setAction("Action", null).show();
            Random rand = new Random();
            int randomIndexCocktail = rand.nextInt(upperbound);
            goToRecipe(allCocktails.get(randomIndexCocktail));
        }
    }

    private void filterCocktails(String dropDownSelected, String str) {
        filteredCocktails.clear();
        for(Cocktail cocktail : allCocktails) {
            if(dropDownSelected.equals("Cocktail")) {
                if(cocktail.getStrDrink().toLowerCase().contains(str.toLowerCase())) {
                    filteredCocktails.add(cocktail);
                }
            } else {
                if(cocktail.getIsIngredientInside(str)) {
                    filteredCocktails.add(cocktail);
                }
            }
        }
        adapter.setCocktails(filteredCocktails);
    }

    private void getCocktailsFromApi() {
        cocktailListViewModel.searchCocktailsApi("");
    }

    private void apiObserverSetup() {
        cocktailListViewModel.getCocktails().observe(this, cocktails -> {
            // observing for changes
            if(cocktails != null) {
                allCocktails = cocktails;
                for(Cocktail c: allCocktails) {
                    c.setAllIngredients();
                    c.setAllMeasures();
                }
                adapter.setCocktails(allCocktails);
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void goToRecipe(Cocktail cocktail) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("myCocktail", cocktail);
        NavHostFragment.findNavController(SearchFragment.this)
                .navigate(R.id.action_SearchFragment_to_RecipeFragment, bundle);
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

    @Override
    public void onResume() {
        super.onResume();
        dropDownSetUp();
    }
}