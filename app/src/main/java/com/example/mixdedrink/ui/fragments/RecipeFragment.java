package com.example.mixdedrink.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mixdedrink.R;
import com.example.mixdedrink.data.remote.dtos.CocktailDto;
import com.example.mixdedrink.databinding.FragmentRecipeBinding;
import com.example.mixdedrink.utils.IngredientMeasureAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class RecipeFragment extends Fragment {
    private FragmentRecipeBinding binding;
    private Context context;
    private IngredientMeasureAdapter adapter;

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
            CocktailDto cocktailDto = getArguments().getParcelable("myCocktail");
            dataSetUp(cocktailDto);
            imageSetUp(cocktailDto.getStrDrinkThumb());
            ingredientsSetUp(cocktailDto);
        }
    }

    private void dataSetUp(CocktailDto cocktailDto) {
        binding.tvDrinkName.setText(cocktailDto.getStrDrink());
        binding.tvCategoryData.setText(cocktailDto.getStrCategory());
        binding.tvGlassData.setText(cocktailDto.getStrGlass());
        binding.tvInstructionsData.setText(cocktailDto.getStrInstructions());
    }

    private void imageSetUp(String url){
        Picasso.get().load(url).placeholder(R.drawable.loading).error(R.drawable.no_pic).into(binding.imageDrink);
    }

    private void ingredientsSetUp(CocktailDto cocktailDto) {
        adapter.setIngredientsMeasures(cocktailDto.getAllIngredients(), cocktailDto.getAllMeasures());
        binding.rvIngredients.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void listenerSetup() {
        /* floating icon setup */
        binding.favoriteBtn.setOnClickListener(view -> {
            Snackbar.make(view, "Add to Favorites", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            //binding.fab.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorites));
        });
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