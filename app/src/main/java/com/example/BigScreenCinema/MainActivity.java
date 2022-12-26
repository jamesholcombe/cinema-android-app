package com.example.BigScreenCinema;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.BigScreenCinema.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseUser;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private static FirebaseUser user;
    private static String userDisplayName;
    private TextView userDisplayNameTextView;
    private MovieView movieModel;
    private SelectedMovieView selectedMovieView;


    public FirebaseUser getUser() {
        return user;
    }

    public static void setUser(FirebaseUser user) {
        MainActivity.user = user;
    }
    public static String getUserDisplayName() {
        return userDisplayName;
    }
    public static void setUserDisplayName(String userDisplayName) {
        MainActivity.userDisplayName = userDisplayName;
    }

    private void setUserText() {

        if (user != null) {

            userDisplayNameTextView = findViewById(R.id.textViewUser);
            String message = getString(R.string.welcome) + userDisplayName;
            userDisplayNameTextView.setText(message);
        }
    }

    private void createViewModels () {
        movieModel = new ViewModelProvider(this).get(MovieView.class);
        selectedMovieView = new ViewModelProvider(this).get(SelectedMovieView.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModels();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        System.out.println("onCreate");
        setUserText();

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        //print user
//        System.out.println("User: " + getUser().getDisplayName());
//
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void openLoginActivity(View view) {
Intent intent = new Intent(this, FirebaseLoginActivity.class);
        startActivity(intent);

        //get result from login activity
        user = FirebaseLoginActivity.getUser();
        setUserText();



    }







}