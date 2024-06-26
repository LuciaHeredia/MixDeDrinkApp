package com.example.mixdedrink.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixdedrink.R;
import com.example.mixdedrink.data.models.Cocktail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailHolder> {
    private List<Cocktail> cocktails = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CocktailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cocktail_item, parent, false);
        return new CocktailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CocktailHolder holder, int position) {
        Cocktail currentCocktail = cocktails.get(position);
        holder.tv_drinkName.setText(currentCocktail.getStrDrink());
    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    public void setCocktails(List<Cocktail> cocktails) {
        // sort cocktails by name in alphabetical order
        Collections.sort(cocktails, new Comparator<Cocktail>() {
            @Override
            public int compare(Cocktail lhs, Cocktail rhs) {
                return lhs.getStrDrink().toLowerCase().compareTo(rhs.getStrDrink().toLowerCase());
            }
        });

        this.cocktails = cocktails;
        notifyDataSetChanged();
    }

    class CocktailHolder extends RecyclerView.ViewHolder {
        private final TextView tv_drinkName;

        public CocktailHolder(@NonNull View itemView) {
            super(itemView);
            tv_drinkName = itemView.findViewById(R.id.tv_drink_name);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(cocktails.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Cocktail cocktail);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
