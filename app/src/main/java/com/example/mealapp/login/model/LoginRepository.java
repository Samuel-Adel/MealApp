package com.example.mealapp.login.model;

import com.example.mealapp.db.FavMealModel;
import com.example.mealapp.db.IMealsLocalDataBase;
import com.example.mealapp.db.MealsLocalDataBaseImpl;
import com.example.mealapp.db.PlannedMealModel;
import com.example.mealapp.home_screen.model.Meal;
import com.example.mealapp.login.network.ILoginRemoteDataSource;
import com.example.mealapp.network.NetworkCallBacks;
import com.example.mealapp.user.UserLoginType;
import com.example.mealapp.user.UserSavedCredentialsManager;
import com.example.mealapp.user.UserType;
import com.example.mealapp.user.backup.FireBaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginRepository implements ILoginRepository {
    private UserType userType;
    private ILoginRemoteDataSource loginRemoteDataSource;
    private UserSavedCredentialsManager userSavedCredentialsManager;
    private static LoginRepository instance = null;
    private IMealsLocalDataBase mealsLocalDataBase;

    public static LoginRepository getInstance(ILoginRemoteDataSource remoteDataSource, UserSavedCredentialsManager userSavedCredentialsManager, IMealsLocalDataBase mealsLocalDataBase) {
        if (instance == null) {
            instance = new LoginRepository(remoteDataSource, userSavedCredentialsManager, mealsLocalDataBase);
        }
        return instance;
    }

    private LoginRepository(ILoginRemoteDataSource loginRemoteDataSource, UserSavedCredentialsManager userSavedCredentialsManager, IMealsLocalDataBase mealsLocalDataBase) {
        this.loginRemoteDataSource = loginRemoteDataSource;
        this.userSavedCredentialsManager = userSavedCredentialsManager;
        this.userType = UserType.getInstance();
        this.mealsLocalDataBase = mealsLocalDataBase;
    }

    @Override
    public void signInUser(NetworkCallBacks networkCallBacks, UserCredentials userCredentials) {
        userType.setUserLoginType(UserLoginType.AuthenticatedUser);
        loginRemoteDataSource.makeNetworkCall(networkCallBacks, userCredentials);
    }

    @Override
    public void signInUserWithGoogle(NetworkCallBacks networkCallBacks, String idToken) {
        loginRemoteDataSource.signUpWithGoogle(networkCallBacks, idToken);
    }

    @Override
    public void signInUserAsGuest() {
        userType.setUserLoginType(UserLoginType.guestUser);
    }

    @Override
    public void saveUserCredentials(String token) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users/" + token);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FireBaseUser user = dataSnapshot.getValue(FireBaseUser.class);
                if (user != null) {
                    if (user.getFavMeals() != null) {
                        System.out.println("Fav Meals:");
                        for (FavMealModel meal : user.getFavMeals()) {
                            new Thread(() -> {
                                mealsLocalDataBase.insertFavMeal(new Meal(meal.getId(), meal.getName(), meal.getImageLink(), true));
                            }).start();
                        }
                    }
                    if (user.getPlannedMeals() != null) {
                        System.out.println("Planned Meals:");
                        for (PlannedMealModel meal : user.getPlannedMeals()) {
                            new Thread(() -> {
                                mealsLocalDataBase.insertMealPlan(new Meal(meal.getId(), meal.getName(), meal.getImageLink(), meal.getMealDay()),meal.getMealDay());
                            }).start();
                        }
                    }


                } else {
                    System.out.println("User not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Database error: " + databaseError.getMessage());
            }
        });

        userSavedCredentialsManager.saveUserToken(token);
    }
}
