package com.example.mealapp.home_screen.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.home_screen.model.Category;
import com.example.mealapp.home_screen.model.Country;
import com.example.mealapp.home_screen.model.FilterItem;
import com.example.mealapp.home_screen.model.Ingredient;
import java.util.List;


public class HomeScreenAdapter<T extends FilterItem> extends RecyclerView.Adapter<HomeScreenAdapter.ViewHolder> {

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

    public HomeScreenAdapter(Context context, List<T> categories, OnFilterItemClickListener onFilterItemClickListener) {
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
        holder.btnItem.setText(filterItems.get(position).getName());
        holder.btnItem.setOnClickListener(v -> {
            if (currentFilter.equals(CurrentFilter.category)) {
                holder.btnItem.setBackgroundTintList(ColorStateList.valueOf(0));
                onFilterItemClickListener.changeFilterSelectedItemCategory((Category) filterItems.get(position));

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