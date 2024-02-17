package com.example.mealapp.meal_details.view;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealapp.R;
import com.example.mealapp.db.FavMealsLocalDataSourceImpl;
import com.example.mealapp.db.IFavMealsLocalDataSource;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.meal_details.model.IMealDetailsRepository;
import com.example.mealapp.meal_details.model.MealDetailsRepositoryImpl;
import com.example.mealapp.meal_details.network.IMealDetailsRemoteDataSource;
import com.example.mealapp.meal_details.network.MealDetailsRemoteDataSourceImpl;
import com.example.mealapp.meal_details.presenter.IMealDetailsPresenter;
import com.example.mealapp.meal_details.presenter.MealDetailsPresenterImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsView {
    private IMealDetailsPresenter mealDetailsPresenter;
    private WebView videoView;
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
        IFavMealsLocalDataSource favMealsLocalDataSource = FavMealsLocalDataSourceImpl.getInstance(this);
        IMealDetailsRemoteDataSource mealDetailsRemoteDataSource = MealDetailsRemoteDataSourceImpl.getInstance();
        IMealDetailsRepository mealDetailsRepository = MealDetailsRepositoryImpl.getInstance(mealDetailsRemoteDataSource, favMealsLocalDataSource);
        mealDetailsPresenter = new MealDetailsPresenterImpl(mealDetailsRepository, this);
        mealDetailsPresenter.getMealDetails(mealId);
        ImageView addToFavImgBtn = findViewById(R.id.addToFavImageView);
        mealImage = findViewById(R.id.detailImageView);
        mealName = findViewById(R.id.detailMealName);
        mealInstructions = findViewById(R.id.detailMealInstructions);
        mealCountry = findViewById(R.id.detailMealCountry);
        videoView = findViewById(R.id.videoView);
        video = findViewById(R.id.detailVideo);
        instructions = findViewById(R.id.detailInstructionsTextView);
        country = findViewById(R.id.detailCountryTextView);
        addToFavImgBtn.setOnClickListener(v -> {
            if (isLoaded) {
                mealDetailsPresenter.addMealToFav(meal);
                Toast.makeText(this, meal.getName() + " added to Favourite", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void showMealDetail(Meal meal) {
        this.meal = meal;
        isLoaded = true;
        Glide.with(this).load(meal.getImageLink()).transform(new RoundedCorners(10)).centerCrop().into(mealImage);
        mealName.setText(meal.getName());
        mealCountry.setText(meal.getCountry());
        mealInstructions.setText(meal.getInstructions());
        video.setText(R.string.video);
        country.setText(R.string.country);
        instructions.setText(R.string.instructions);
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
}