package com.example.mixdedrink.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mixdedrink.R;
import com.example.mixdedrink.data.models.Cocktail;
import com.example.mixdedrink.databinding.FragmentRecipeBinding;
import com.example.mixdedrink.presentation.FavoriteViewModel;
import com.example.mixdedrink.utils.IngredientMeasureAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class RecipeFragment extends Fragment {
    private FragmentRecipeBinding binding;
    private Cocktail currentCocktail;
    private IngredientMeasureAdapter adapter;
    private FavoriteViewModel favoriteViewModel;
    private boolean isCurrentFavorite;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        recyclerViewIngredientsSetup();
        recipeSetUp();
        listenerSetup();
        return binding.getRoot();
    }

    private void recyclerViewIngredientsSetup() {
        binding.rvIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvIngredients.setHasFixedSize(true);
        adapter = new IngredientMeasureAdapter();
        binding.rvIngredients.setAdapter(adapter);
    }

    private void recipeSetUp(){
        if (getArguments() != null) {
            currentCocktail = getArguments().getParcelable("myCocktail");
            isFavorite(currentCocktail.getIdDrink());
            dataSetUp();
            imageSetUp(currentCocktail.getStrDrinkThumb());
            ingredientsSetUp();
        }
    }

    private void isFavorite(String idDrink) {
        favoriteViewModel.getAllFavorites().observe(this, dbFavorites -> {
            isCurrentFavorite = false;
            for(Cocktail c: dbFavorites) {
                if (c.getIdDrink().equals(idDrink)) {
                    isCurrentFavorite = true;
                    break;
                }
            }

            if(isCurrentFavorite) {
                binding.favoriteBtn.setImageResource(R.drawable.ic_favorites_remove);
            } else {
                binding.favoriteBtn.setImageResource(R.drawable.ic_favorites_add);
            }
        });
    }

    private void dataSetUp() {
        binding.tvDrinkName.setText(currentCocktail.getStrDrink());
        binding.tvCategoryData.setText(currentCocktail.getStrCategory());
        binding.tvGlassData.setText(currentCocktail.getStrGlass());
        binding.tvInstructionsData.setText(currentCocktail.getStrInstructions());
    }

    private void imageSetUp(String url){
        Picasso.get().load(url).placeholder(R.drawable.loading).error(R.drawable.no_pic).into(binding.imageDrink);
    }

    private void ingredientsSetUp() {
        if(currentCocktail.getAllIngredients().isEmpty()) {
            // Recipe from DB
            currentCocktail.setAllIngredients();
            currentCocktail.setAllMeasures();
        }
        adapter.setIngredientsMeasures(currentCocktail.getAllIngredients(), currentCocktail.getAllMeasures());
        binding.rvIngredients.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void listenerSetup() {
        /* Floating Icon - Favorite */
        binding.favoriteBtn.setOnClickListener(view -> {
            if(isCurrentFavorite) {
                deleteAsFavoriteFromDb(view);
            } else {
                saveAsFavoriteInDb(view);
            }
        });
    }

    private void deleteAsFavoriteFromDb(View view) {
        Snackbar.make(view, "Deleted from Favorites", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        favoriteViewModel.deleteFavorite(currentCocktail);
    }

    private void saveAsFavoriteInDb(View view) {
        Snackbar.make(view, "Added to Favorites", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        favoriteViewModel.insertFavorite(currentCocktail);
    }

    @Override
    public void onStop() {
        super.onStop();
        // show Toolbar when exit fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}