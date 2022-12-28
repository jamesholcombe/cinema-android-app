package com.example.BigScreenCinema;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.BigScreenCinema.ViewModels.DataModels.Movie;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    ArrayList<Movie> list;
    private SelectedMovieView selectedMovieView;
    private NavController navController;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView movieImageView;
        public TextView movieTitleTextView;
        public RatingBar movieRatingBar;
        public Button movieMoreInfo;
        public Button movieScreeningsButton;



        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find each view by id you set up in the list_item.xml
            movieImageView = itemView.findViewById(R.id.image_view_movie);
            movieTitleTextView = itemView.findViewById(R.id.text_view_movie_title);
            movieRatingBar = itemView.findViewById(R.id.ratingBar);
            movieMoreInfo = itemView.findViewById(R.id.button_movie_more_info);
            movieScreeningsButton = itemView.findViewById(R.id.button_movie_tickets);

        }
    }

    // Constructor
    public MovieAdapter(ArrayList<Movie> list, NavController navController, SelectedMovieView selectedMovieView){
        this.list = list;
        this.navController = navController;
        this.selectedMovieView = selectedMovieView;

    }

    // Creating a viewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        // Get the Movie based on the current position
        Movie currentItem = list.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.movieImageView;
        DownloadImageFromUri downloadImageFromUri = new DownloadImageFromUri(imageView);
        downloadImageFromUri.execute(currentItem.getImageUri());

        TextView textView = holder.movieTitleTextView;
        textView.setText(currentItem.getTitle());

        RatingBar ratingBar = holder.movieRatingBar;
        ratingBar.setRating(currentItem.getRating() / 2);

        holder.movieScreeningsButton.setOnClickListener(view -> {

            selectedMovieView.setMovie(currentItem);
            navController.navigate(R.id.action_SecondFragment_to_screeningsFragment);
        });

        holder.movieMoreInfo.setOnClickListener(view -> {

            selectedMovieView.setMovie(currentItem);
                    navController.navigate(R.id.action_SecondFragment_to_detaliedFragment);
        });
    }



    // Indicating how long your data is
    @Override
    public int getItemCount() {
        return list.size();
    }
}