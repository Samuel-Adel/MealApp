package com.example.mealapp.favourite_meals.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealapp.R;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.home_screen.view.HomeScreenMealsAdapter;
import com.example.mealapp.network.NetworkConnectivity;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsAdapter.ViewHolder> {

    private final Context context;

    private List<Meal> meals;
    private final onFavScreenClickListener onFavScreenClickListener;

    private byte[] convertedImage;
    private ByteArrayOutputStream imageConverter;

    public FavMealsAdapter(Context context, List<Meal> meals, onFavScreenClickListener onFavScreenClickListener) {
        this.context = context;
        this.meals = meals;
        this.onFavScreenClickListener = onFavScreenClickListener;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.meal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMealsAdapter.ViewHolder holder, int position) {
        Log.i("Check image resource ", "onBindViewHolder: " + Arrays.toString(meals.get(position).getImage()));
        boolean isConnected = NetworkConnectivity.isNetworkAvailable(context);
        if (isConnected) {
            Glide.with(context).asBitmap().load(meals.get(position).getImageLink()).override(120, 120).transform(new RoundedCorners(10)).centerCrop().into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    imageConverter = new ByteArrayOutputStream();
                    resource.compress(Bitmap.CompressFormat.PNG, 100, imageConverter);
                    convertedImage = imageConverter.toByteArray();
                    holder.mealImage.setImageBitmap(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });
            meals.get(position).setImage(convertedImage);
        } else {
            if (meals.get(position).getImage() != null) {
                byte[] byteArray = meals.get(position).getImage();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                holder.mealImage.setImageBitmap(bitmap);
            } else {
                holder.mealImage.setImageResource(R.drawable.food_holder);
            }
        }

//        Glide.with(context).asBitmap().load(meals.get(position).getImageLink()).
//                override(120, 120).transform(new RoundedCorners(10)).centerCrop().into(holder.mealImage);
//
        holder.mealName.setText(meals.get(position).getName());
        holder.favIcon.setImageResource(R.drawable.remove_from_fav);
        holder.favIcon.setOnClickListener(v -> {
            onFavScreenClickListener.removeFromFavItems(meals.get(position));
        });
        holder.cardView.setOnClickListener(v -> {
            onFavScreenClickListener.showMealDetails(meals.get(position));
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
