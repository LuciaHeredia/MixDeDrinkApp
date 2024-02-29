package com.example.mixdedrink.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mixdedrink.databinding.FragmentRecipeBinding;
import com.google.android.material.snackbar.Snackbar;

public class RecipeFragment extends Fragment {
    private FragmentRecipeBinding binding;
    private Context context;

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
        listenerSetup();
        return binding.getRoot();
    }

    private void listenerSetup() {
        // floating icon setup
        binding.fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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