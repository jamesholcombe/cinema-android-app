package com.example.BigScreenCinema;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeaturedMovieAdapter extends RecyclerView.Adapter<FeaturedMovieAdapter.ViewHolder> {

    ArrayList<Movie> list;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView featuredMovieImageView;
        public TextView featuredMovieTitleTextView;


        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find each view by id you set up in the list_item.xml
            featuredMovieImageView = itemView.findViewById(R.id.image_view_featured_movie);
            featuredMovieTitleTextView = itemView.findViewById(R.id.text_view_featured_movie_title);

        }
    }

    // Constructor
    public FeaturedMovieAdapter(ArrayList<Movie> list){
        this.list = list;
    }

    // Creating a viewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.featured_movie_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull FeaturedMovieAdapter.ViewHolder holder, int position) {
        // Get the Movie based on the current position
        Movie currentItem = list.get(position);

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
}