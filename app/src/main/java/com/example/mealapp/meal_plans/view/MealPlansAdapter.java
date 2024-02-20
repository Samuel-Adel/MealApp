package com.example.mealapp.meal_plans.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class MealPlansAdapter extends RecyclerView.Adapter<MealPlansAdapter.ViewHolder> {

    private final Context context;

    private List<Meal> meals;
    private final OnPlansMealClickListener onPlansMealClickListener;


    public MealPlansAdapter(Context context, List<Meal> meals, OnPlansMealClickListener onPlansMealClickListener) {
        this.context = context;
        this.meals = meals;
        this.onPlansMealClickListener = onPlansMealClickListener;
    }

    public void updateData(List<Meal> viewModels) {
        meals = viewModels;
        notifyDataSetChanged();
    }

    public void addItem(int position, Meal viewModel) {
        meals.add(position, viewModel);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        meals.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MealPlansAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.plan_meal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealPlansAdapter.ViewHolder holder, int position) {


        Glide.with(context).load(meals.get(position).getImageLink()).override(120, 120).transform(new RoundedCorners(10)).centerCrop().into(holder.mealImage);

        holder.mealName.setText(meals.get(position).getName());

        holder.removeFromPLans.setOnClickListener(v -> {
            onPlansMealClickListener.onRemoveMealButtonClicked(meals.get(position));
        });
        holder.cardView.setOnClickListener(v -> {
            onPlansMealClickListener.showMealDetails(meals.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mealImage;
        private final Button removeFromPLans;
        private final TextView mealName;
        private final CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mealImage = itemView.findViewById(R.id.mealImagePlan);
            this.removeFromPLans = itemView.findViewById(R.id.removeFromPlanButton);
            this.mealName = itemView.findViewById(R.id.mealNamePlan);
            this.cardView = itemView.findViewById(R.id.cardViewMealPlan);
        }
    }
}
