package com.example.BigScreenCinema;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.BigScreenCinema.ViewModels.BookingView;
import com.example.BigScreenCinema.ViewModels.CardsView;
import com.example.BigScreenCinema.ViewModels.DataModels.Booking;
import com.example.BigScreenCinema.ViewModels.DataModels.User;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.ViewModels.LiveBookingView;
import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.ViewModels.UserView;
import com.example.BigScreenCinema.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextView userDisplayNameTextView;
    private MovieView movieModel;
    private SelectedMovieView selectedMovieView;
    private CardsView cardsView;
    private UserView userView;
    private GlobalDataView globalDataView;
    private BookingView bookingView;
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(new FirebaseAuthUIActivityResultContract(), new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
        @Override
        public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
            onSignInResult(result);
        }
    });
    private LiveBookingView liveBookingView;

    private void createViewModels() {
        movieModel = new ViewModelProvider(this).get(MovieView.class);
        selectedMovieView = new ViewModelProvider(this).get(SelectedMovieView.class);
        globalDataView = new ViewModelProvider(this).get(GlobalDataView.class);
        userView = new ViewModelProvider(this).get(UserView.class);
        liveBookingView = new ViewModelProvider(this).get(LiveBookingView.class);
        cardsView = new ViewModelProvider(this).get(CardsView.class);
        bookingView = new ViewModelProvider(this).get(BookingView.class);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModels();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        globalDataView.getFirebaseUser().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                String displayMessage = "Welcome " + firebaseUser.getDisplayName();
                binding.textViewUser.setText(displayMessage);
            }
        });

        globalDataView.getFragmentName().observe(this, fragmentName -> {
            if (fragmentName != null) {
                binding.toolbar.setTitle(fragmentName);
            }
        });

        binding.imageButtonUser.setOnClickListener(v -> {
            openLoginActivity();

        });


        setSupportActionBar(binding.toolbar);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    private void createOrRetrieveUser(String id) {
        userView.getDb().collection("users").document(id).get().addOnCompleteListener(task -> {
            Gson gson = new Gson();
            Map<String, Object> data = task.getResult().getData();

            // if the user has no bookings or cards, they will not be in the database
            if (Objects.isNull(data)) {
                Booking[] books = {};
                User user = new User(id, books);
                globalDataView.setUser(user);
                cardsView.setUser(user);
                bookingView.setUser(user);

            } else {

                String json = gson.toJson(data);
                User user = gson.fromJson(json, User.class);
                globalDataView.setUser(user);
                cardsView.setUser(user);
                bookingView.setUser(user);
            }
        });

    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            globalDataView.setFirebaseUser(firebaseUser);
            createOrRetrieveUser(firebaseUser.getUid());

            // ...
        } else {
            System.out.println("LOGIN FAILED");
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(), new AuthUI.IdpConfig.GoogleBuilder().build()

        );

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build();
        signInLauncher.launch(signInIntent);
        // [END auth_fui_create_intent]
    }


    public void openLoginActivity() {
        createSignInIntent();

    }


}