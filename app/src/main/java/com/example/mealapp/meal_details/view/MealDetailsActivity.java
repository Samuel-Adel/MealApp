package com.example.mealapp.meal_details.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealapp.R;
import com.example.mealapp.db.MealsLocalDataBaseImpl;
import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.model.IMealDetailsRepository;
import com.example.mealapp.meal_details.model.MealDetailsRepositoryImpl;
import com.example.mealapp.meal_details.network.IMealDetailsRemoteDataSource;
import com.example.mealapp.meal_details.network.MealDetailsRemoteDataSourceImpl;
import com.example.mealapp.meal_details.presenter.IMealDetailsPresenter;
import com.example.mealapp.meal_details.presenter.MealDetailsPresenterImpl;
import com.example.mealapp.meal_plans.model.Days;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsView {
    private IMealDetailsPresenter mealDetailsPresenter;
    private WebView videoView;
    private ImageView addToFavImgBtn;
    private ImageView isPlannedImageView;
    private ImageView mealImage;
    private TextView mealName;
    private TextView mealCountry;
    private TextView mealInstructions;
    private TextView instructions;
    private TextView country;
    private TextView video;
    private ProgressBar progressBar;
    private Meal meal;
    private boolean isLoaded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        ImageView backButtonImgView = findViewById(R.id.BackButtonImageVie);
        backButtonImgView.setOnClickListener(v -> finish());
        String mealId = getIntent().getStringExtra("meal_id");
        progressBar = findViewById(R.id.detailsMealProgressBar);
        IMealsLocalDataBase favMealsLocalDataSource = MealsLocalDataBaseImpl.getInstance(this);
        IMealDetailsRemoteDataSource mealDetailsRemoteDataSource = MealDetailsRemoteDataSourceImpl.getInstance();
        IMealDetailsRepository mealDetailsRepository = MealDetailsRepositoryImpl.getInstance(mealDetailsRemoteDataSource, favMealsLocalDataSource);
        mealDetailsPresenter = new MealDetailsPresenterImpl(mealDetailsRepository, this);
        mealDetailsPresenter.getMealDetails(mealId);
        addToFavImgBtn = findViewById(R.id.addToFavImageView);
        isPlannedImageView = findViewById(R.id.addToPlansDetailsImgView);
        mealImage = findViewById(R.id.detailImageView);
        mealName = findViewById(R.id.detailMealName);
        mealInstructions = findViewById(R.id.detailMealInstructions);
        mealCountry = findViewById(R.id.detailMealCountry);
        videoView = findViewById(R.id.videoView);
        video = findViewById(R.id.detailVideo);
        instructions = findViewById(R.id.detailInstructionsTextView);
        country = findViewById(R.id.detailCountryTextView);
        isPlannedImageView = findViewById(R.id.addToPlansDetailsImgView);
        addToFavImgBtn.setOnClickListener(v -> {
            if (isLoaded) {
                addOrRemove();
                //   mealDetailsPresenter.checkToAddOrRemoveMealFromFav(meal);
            }
        });
        isPlannedImageView.setOnClickListener(v -> {
            showWeekdayMenu(v);
        });

    }

    @Override
    public void showMealDetail(Meal meal) {
        this.meal = meal;
        Glide.with(this).load(meal.getImageLink()).transform(new RoundedCorners(10)).centerCrop().into(mealImage);
        mealName.setText(meal.getName());
        mealCountry.setText(meal.getCountry());
        mealInstructions.setText(meal.getInstructions());
        video.setText(R.string.video);
        country.setText(R.string.country);
        instructions.setText(R.string.instructions);
        Log.i("Meal Details", "showMealDetail: fav or not " + meal.isFav());
        mealDetailsPresenter.checkIfMealIsFav(meal);
        String videoId = extractVideoId(meal.getVideoLink());
        Log.i("MealYoutueVideo", "showMealDetail: " + meal.getVideoLink());
        String embedUrl = "https://www.youtube.com/embed/" + videoId;
        String html = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + embedUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        videoView.loadData(html, "text/html", "utf-8");
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.setWebChromeClient(new WebChromeClient());

    }

    private String extractVideoId(String youtubeUrl) {
        String videoId = null;
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("https")) {
            String expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2Fvideos%2F|youtu.be%2F|watch\\?v=|v=|u\\/\\w\\/|embed\\?video_id=|video_id=|\\/embed\\/|\\/v\\/|embed\\?video_id=|\\?v=)([\\w-]+)";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(youtubeUrl);
            if (matcher.find()) {
                videoId = matcher.group(1);
            }
        }
        return videoId;
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showOrHideProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showIfMealISFav(Flowable<Boolean> isFav) {
        isLoaded = true;
        isFav.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            meal.setFav(item.booleanValue());
            Log.i("Meal Details", "showMealDetail: fav or not " + item.booleanValue());
            if (item.booleanValue() == true) {
                addToFavImgBtn.setImageResource(R.drawable.remove_from_fav);
            }
        });
    }

    private void addOrRemove() {
        if (meal.isFav()) {
            mealDetailsPresenter.removeMealFromFav(meal);
            addToFavImgBtn.setImageResource(R.drawable.add_to_fav);
            Toast.makeText(this, "Meal removed from Favourites", Toast.LENGTH_SHORT).show();
            meal.setFav(false);
        } else {
            mealDetailsPresenter.addMealToFav(meal);
            addToFavImgBtn.setImageResource(R.drawable.remove_from_fav);
            Toast.makeText(this, "Meal added to Favourite", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showIfMealIsPlanned(Flowable<Boolean> isPlanned) {
        isPlanned.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {

                }

        );
    }

    private void showWeekdayMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.days_pop_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.monday) {
                    mealDetailsPresenter.addMealToPlan(meal, Days.monday);
                    showAddToPlanToast(Days.monday.name());
                    return true;
                } else if (itemId == R.id.tuesday) {
                    mealDetailsPresenter.addMealToPlan(meal, Days.tuesday);
                    showAddToPlanToast(Days.tuesday.name());
                    return true;
                } else if (itemId == R.id.wednesday) {
                    mealDetailsPresenter.addMealToPlan(meal, Days.wednesday);
                    showAddToPlanToast(Days.wednesday.name());
                    return true;
                } else if (itemId == R.id.thursday) {
                    mealDetailsPresenter.addMealToPlan(meal, Days.thursday);
                    showAddToPlanToast(Days.tuesday.name());
                    return true;
                } else if (itemId == R.id.friday) {
                    mealDetailsPresenter.addMealToPlan(meal, Days.friday);
                    showAddToPlanToast(Days.friday.name());
                    return true;
                } else if (itemId == R.id.saturday) {
                    mealDetailsPresenter.addMealToPlan(meal, Days.saturday);
                    showAddToPlanToast(Days.saturday.name());
                    return true;
                } else if (itemId == R.id.sunday) {
                    mealDetailsPresenter.addMealToPlan(meal, Days.sunday);
                    showAddToPlanToast(Days.sunday.name());
                    return true;
                } else {
                    return false;
                }
            }
        });

        popup.show();
    }
    private void showAddToPlanToast(String msg){
        Toast.makeText(this,"Added to "+msg+" plan", Toast.LENGTH_SHORT).show();

    }


}