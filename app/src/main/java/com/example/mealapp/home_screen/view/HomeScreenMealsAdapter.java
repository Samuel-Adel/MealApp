package com.example.mealapp.home_screen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealapp.R;
import com.example.mealapp.home_screen.model.Meal;

import java.util.List;

public class HomeScreenMealsAdapter extends RecyclerView.Adapter<HomeScreenMealsAdapter.ViewHolder> {

    private final Context context;

    private List<Meal> meals;
    private final OnFilterItemClickListener onFilterItemClickListener;


    public HomeScreenMealsAdapter(Context context, List<Meal> meals, OnFilterItemClickListener onFilterItemClickListener) {
        this.context = context;
        this.meals = meals;
        this.onFilterItemClickListener = onFilterItemClickListener;
    }

    public void updateData(List<Meal> viewModels) {
        meals = viewModels;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.meal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenMealsAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(meals.get(position).getImageLink()).override(120, 120).transform(new RoundedCorners(10)).centerCrop().into(holder.mealImage);
        holder.mealName.setText(meals.get(position).getName());
        holder.favIcon.setImageResource(R.drawable.add_to_fav);
        holder.favIcon.setOnClickListener(v -> {
            onFilterItemClickListener.addMealToFav(meals.get(position));
        });
        holder.cardView.setOnClickListener(v -> {
            onFilterItemClickListener.showMealDetails(meals.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mealImage;
        private final ImageView favIcon;
        private final TextView mealName;
        private final CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mealImage = itemView.findViewById(R.id.mealImage);
            this.favIcon = itemView.findViewById(R.id.favIcon);
            this.mealName = itemView.findViewById(R.id.mealName);
            this.cardView = itemView.findViewById(R.id.cardViewMeal);
        }
    }
}
