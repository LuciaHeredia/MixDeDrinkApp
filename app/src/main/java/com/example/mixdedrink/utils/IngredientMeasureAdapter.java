package com.example.mixdedrink.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mixdedrink.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientMeasureAdapter extends RecyclerView.Adapter<IngredientMeasureAdapter.IngredientMeasureHolder>{
    private List<String> ingredientsMeasures = new ArrayList<>();

    @NonNull
    @Override
    public IngredientMeasureAdapter.IngredientMeasureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_measure_item, parent, false);
        return new IngredientMeasureAdapter.IngredientMeasureHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientMeasureAdapter.IngredientMeasureHolder holder, int position) {
        String ingredientMeasure = ingredientsMeasures.get(position);
        holder.tv_ingredientMeasure.setText(ingredientMeasure);
    }

    @Override
    public int getItemCount() {
        return ingredientsMeasures.size();
    }

    public void setIngredientsMeasures(List<String> ingredients, List<String> measures) {
        // arrange string
        List<String> all = new ArrayList<>();
        for(int i=0; i<ingredients.size(); i++) {
            if(ingredients.get(i)!=null) {
                all.add(ingredients.get(i)+" - "+measures.get(i));
            }
        }

        this.ingredientsMeasures = all;
        notifyDataSetChanged();
    }

    class IngredientMeasureHolder extends RecyclerView.ViewHolder {
        private final TextView tv_ingredientMeasure;

        public IngredientMeasureHolder(@NonNull View itemView) {
            super(itemView);
            tv_ingredientMeasure = itemView.findViewById(R.id.tv_i_m_item);
        }
    }

}
