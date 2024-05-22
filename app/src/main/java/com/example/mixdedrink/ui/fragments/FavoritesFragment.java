package com.example.mixdedrink.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mixdedrink.R;
import com.example.mixdedrink.data.models.Cocktail;
import com.example.mixdedrink.databinding.FragmentFavoritesBinding;
import com.example.mixdedrink.presentation.FavoriteViewModel;
import com.example.mixdedrink.utils.CocktailAdapter;

public class FavoritesFragment extends Fragment {
    private FragmentFavoritesBinding binding;
    private CocktailAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        recyclerViewSetup();
        initFavoritesFromDb();
        return binding.getRoot();
    }

    private void recyclerViewSetup() {
        binding.recyclerViewFav.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewFav.setHasFixedSize(true);
        adapter = new CocktailAdapter();
        binding.recyclerViewFav.setAdapter(adapter);
        adapter.setOnItemClickListener(this::sendCocktailData);
    }

    private void initFavoritesFromDb() {
        FavoriteViewModel favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        favoriteViewModel.getAllFavorites().observe(this, favorites -> {
            adapter.setCocktails(favorites);
            binding.recyclerViewFav.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);

            if(favorites.isEmpty()) {
                binding.txEmpty.setVisibility(View.VISIBLE);
            }
        });
    }

    private void sendCocktailData(Cocktail cocktail) {
        goToRecipe(cocktail);
    }

    private void goToRecipe(Cocktail cocktail) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("myCocktail", cocktail);
        NavHostFragment.findNavController(FavoritesFragment.this)
                .navigate(R.id.action_favoritesFragment_to_recipeFragment, bundle);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        /* current icon tapped: Favorites */
        MenuItem item = menu.findItem(R.id.favoritesFragment);
        if (item != null) {
            item.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_favorites_tapped, null));
            item.setEnabled(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
