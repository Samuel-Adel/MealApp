package com.example.mealapp.home_screen.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealapp.R;
import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.FilterItem;
import com.example.mealapp.home_screen.model.Ingredient;

import java.util.List;


public class HomeScreenFilterAdapter<T extends FilterItem> extends RecyclerView.Adapter<HomeScreenFilterAdapter.ViewHolder> {

    private final Context context;

    private List<T> filterItems;
    private final OnFilterItemClickListener onFilterItemClickListener;
    private CurrentFilter currentFilter;

    public CurrentFilter getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(CurrentFilter currentFilter) {
        this.currentFilter = currentFilter;
    }

    private T currentChoice = null;

    public T getCurrentChoice() {
        return currentChoice;
    }

    public void setCurrentChoice(T currentChoice) {
        this.currentChoice = currentChoice;
        notifyDataSetChanged();
    }

    public HomeScreenFilterAdapter(Context context, List<T> categories, OnFilterItemClickListener onFilterItemClickListener) {
        this.context = context;
        this.filterItems = categories;
        this.onFilterItemClickListener = onFilterItemClickListener;
    }

    public void updateData(List<T> viewModels) {
        filterItems = viewModels;
        notifyDataSetChanged();
    }

    public void addItem(int position, T viewModel) {
        filterItems.add(position, viewModel);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        filterItems.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.filter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (currentChoice != null && currentChoice.equals(filterItems.get(position))) {
            holder.btnItem.setBackgroundColor(Color.parseColor("#E4AC4F"));
            holder.btnItem.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.btnItem.setBackgroundColor(Color.parseColor("#E4E4E4"));
            holder.btnItem.setTextColor(Color.parseColor("#424242"));
        }
        holder.btnItem.setText(filterItems.get(position).getName());
        holder.btnItem.setOnClickListener(v -> {
            if (currentFilter.equals(CurrentFilter.category)) {
                currentChoice = filterItems.get(position);
                notifyDataSetChanged();
                onFilterItemClickListener.changeFilterSelectedItemCategory((Category) filterItems.get(position));
            } else if (currentFilter.equals(CurrentFilter.country)) {
                currentChoice = filterItems.get(position);
                notifyDataSetChanged();
                onFilterItemClickListener.changeFilterSelectedItemCountry((Country) filterItems.get(position));
            } else {
                currentChoice = filterItems.get(position);
                notifyDataSetChanged();
                onFilterItemClickListener.changeFilterSelectedItemIngredient((Ingredient) filterItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Button btnItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnItem = itemView.findViewById(R.id.btnItem);

        }
    }
}

enum CurrentFilter {
    country, category, ingredient
}