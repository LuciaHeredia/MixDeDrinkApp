package com.example.mixdedrink.ui.fragments;

import android.content.Context;
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
    private Context context;
    private IngredientMeasureAdapter adapter;
    private FavoriteViewModel favoriteViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        recyclerViewSetup();
        recipeSetUp();
        listenerSetup();
        return binding.getRoot();
    }

    private void recyclerViewSetup() {
        binding.rvIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvIngredients.setHasFixedSize(true);
        adapter = new IngredientMeasureAdapter();
        binding.rvIngredients.setAdapter(adapter);
    }

    private void recipeSetUp(){
        if (getArguments() != null) {
            currentCocktail = getArguments().getParcelable("myCocktail");
            dataSetUp();
            imageSetUp(currentCocktail.getStrDrinkThumb());
            ingredientsSetUp();
        }
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
        adapter.setIngredientsMeasures(currentCocktail.getAllIngredients(), currentCocktail.getAllMeasures());
        binding.rvIngredients.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void listenerSetup() {
        /* floating icon setup */
        binding.favoriteBtn.setOnClickListener(view -> {
            Snackbar.make(view, "Added to Favorites", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            saveAsFavoriteInDb();
        });
    }

    private void saveAsFavoriteInDb() {
        favoriteViewModel.insertFavorite(currentCocktail);
        binding.favoriteBtn.setImageResource(R.drawable.ic_favorites_remove);
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