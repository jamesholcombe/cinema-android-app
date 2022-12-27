package com.example.BigScreenCinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.BigScreenCinema.ViewModels.LiveBookingView;
import com.example.BigScreenCinema.ViewModels.Movie;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentCheckoutBinding;

public class CheckoutFragment extends Fragment {
    FragmentCheckoutBinding binding;
    LiveBookingView liveBookingView;
    SelectedMovieView selectedMovieView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false);
        liveBookingView = new ViewModelProvider(requireActivity()).get(LiveBookingView.class);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        liveBookingView.getTotalFormatted().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String price) {

                binding.checkoutTotal.setText(price);
            }
        });
        liveBookingView.getNumChildTickets().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer num) {

                binding.textNumChildCheckout.setText(String.valueOf(num));
            }
        });
        liveBookingView.getNumAdultTickets().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer num) {

                binding.textNumAdultsCheckout.setText(String.valueOf(num));
            }
        });

        selectedMovieView.getMovie().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                binding.textViewMovieTitle.setText(movie.getTitle());
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}