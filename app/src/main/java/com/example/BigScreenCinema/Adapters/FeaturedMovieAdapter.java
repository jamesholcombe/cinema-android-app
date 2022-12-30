package com.example.BigScreenCinema.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.BigScreenCinema.R;
import com.example.BigScreenCinema.Utils.DownloadImageFromUri;
import com.example.BigScreenCinema.ViewModels.DataModels.Movie;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;

import java.util.ArrayList;

public class FeaturedMovieAdapter extends RecyclerView.Adapter<FeaturedMovieAdapter.ViewHolder> {

    ArrayList<Movie> list;
    SelectedMovieView selectedMovieView;
    NavController navController;

    public FeaturedMovieAdapter(ArrayList<Movie> list, NavController navController, SelectedMovieView selectedMovieView) {
        this.list = list;
        this.selectedMovieView = selectedMovieView;
        this.navController = navController;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_movie_item, parent, false);


        return new ViewHolder(contactView);
    }


    @Override
    public void onBindViewHolder(@NonNull FeaturedMovieAdapter.ViewHolder holder, int position) {
        Movie currentItem = list.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMovieView.setMovie(currentItem);
                navController.navigate(R.id.action_FirstFragment_to_detaliedFragment);
            }
        });

        // Setting views with the corresponding data
        ImageView imageView = holder.featuredMovieImageView;
        DownloadImageFromUri downloadImageFromUri = new DownloadImageFromUri(imageView);
        downloadImageFromUri.execute(currentItem.getImageUri());

        TextView textView = holder.featuredMovieTitleTextView;
        textView.setText(currentItem.getTitle());

    }

    // Indicating how long your data is
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView featuredMovieImageView;
        public TextView featuredMovieTitleTextView;
        public View cardView;


        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find each view by id you set up in the list_item.xml
            featuredMovieImageView = itemView.findViewById(R.id.image_view_featured_movie);
            featuredMovieTitleTextView = itemView.findViewById(R.id.text_view_featured_movie_title);
            cardView = itemView.findViewById(R.id.card_view_featured);


        }
    }
}