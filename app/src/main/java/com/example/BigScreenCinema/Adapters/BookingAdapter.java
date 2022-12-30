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
import com.example.BigScreenCinema.ViewModels.BookingView;
import com.example.BigScreenCinema.ViewModels.DataModels.Booking;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    ArrayList<Booking> list;
    BookingView bookingView;
    NavController navController;

    // Constructor
    public BookingAdapter(ArrayList<Booking> list, BookingView bookingView, NavController navController) {
        this.list = list;
        this.bookingView = bookingView;
        this.navController = navController;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }


    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        // Get the Movie based on the current position
        Booking currentItem = list.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.bookingImageView;
        DownloadImageFromUri downloadImageFromUri = new DownloadImageFromUri(imageView);
        downloadImageFromUri.execute(currentItem.getScreening().getMovie().getImageUri());

        holder.bookingDateTextView.setText(currentItem.getScreening().getDateString());
        holder.bookingNumAdultsTextView.setText(String.valueOf(currentItem.getAdultTickets().size()));
        holder.bookingNumChildrenTextView.setText(String.valueOf(currentItem.getChildTickets().size()));
        holder.bookingTitleTextView.setText(currentItem.getScreening().getMovie().getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingView.setSelectedBooking(currentItem);
                navController.navigate(R.id.action_bookingsFragment_to_ticketsFragment);
            }
        });


    }

    // Indicating how long your data is
    @Override
    public int getItemCount() {
        return list.size();
    }

    ;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView bookingImageView;
        public TextView bookingTitleTextView;
        public TextView bookingDateTextView;
        public TextView bookingNumAdultsTextView;
        public TextView bookingNumChildrenTextView;
        public View cardView;


        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingImageView = itemView.findViewById(R.id.image_view_movie_booking);
            bookingTitleTextView = itemView.findViewById(R.id.text_view_movie_title_booking);
            bookingDateTextView = itemView.findViewById(R.id.date_booking);
            bookingNumAdultsTextView = itemView.findViewById(R.id.num_adults_booking);
            bookingNumChildrenTextView = itemView.findViewById(R.id.num_children_booking);
            cardView = itemView.findViewById(R.id.card_booking);

            // Find each view by id you set up in the list_item.xml
        }
    }


}