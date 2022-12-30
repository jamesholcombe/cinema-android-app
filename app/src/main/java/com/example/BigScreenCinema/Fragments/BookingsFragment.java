package com.example.BigScreenCinema.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.BigScreenCinema.Adapters.BookingAdapter;
import com.example.BigScreenCinema.ViewModels.BookingView;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.databinding.FragmentBookingsBinding;


public class BookingsFragment extends Fragment {

    private FragmentBookingsBinding binding;
    private BookingView bookingView;
    private GlobalDataView globalDataView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookingsBinding.inflate(inflater, container, false);
        bookingView = new ViewModelProvider(requireActivity()).get(BookingView.class);
        globalDataView = new ViewModelProvider(requireActivity()).get(GlobalDataView.class);
        globalDataView.setFragmentName("Bookings");
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookingView.getItems().observe(getViewLifecycleOwner(), bookings -> {
            BookingAdapter adapter = new BookingAdapter(bookings, bookingView, NavHostFragment.findNavController(this));
            binding.recyclerViewBookings.setAdapter(adapter);

            // Update the UI
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
