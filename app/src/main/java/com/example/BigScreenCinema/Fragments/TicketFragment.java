package com.example.BigScreenCinema.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.BigScreenCinema.Utils.DownloadImageFromUri;
import com.example.BigScreenCinema.ViewModels.BookingView;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.databinding.FragmentTicketBinding;

public class TicketFragment extends Fragment {
    private FragmentTicketBinding binding;
    BookingView bookingView;
    private GlobalDataView globalDataView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTicketBinding.inflate(inflater, container, false);
        bookingView = new ViewModelProvider(requireActivity()).get(BookingView.class);
        globalDataView = new ViewModelProvider(requireActivity()).get(GlobalDataView.class);
        globalDataView.setFragmentName("Ticket");

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookingView.getSelectedBooking().observe(getViewLifecycleOwner(), booking -> {

            binding.textViewAdultsTicket.setText(String.valueOf(booking.getAdultTickets().size()));
            binding.textViewChildrenTicket.setText(String.valueOf(booking.getChildTickets().size()));
            binding.textViewMovieTitleTicket.setText(booking.getScreening().getMovie().getTitle());
            binding.textViewDateTicket.setText(booking.getScreening().getDateString());
            System.out.println(booking.getQRCodeUri());
            DownloadImageFromUri downloadImageFromUri = new DownloadImageFromUri(binding.imageViewQrCode);
            downloadImageFromUri.execute(booking.getQRCodeUri());
        });





    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
