package com.example.BigScreenCinema.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.BigScreenCinema.Adapters.ScreeningAdapter;
import com.example.BigScreenCinema.MainActivity;
import com.example.BigScreenCinema.R;
import com.example.BigScreenCinema.ViewModels.DataModels.Screening;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.AdultTicket;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.ChildTicket;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.ViewModels.LiveBookingView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentScreeningsBinding;

import java.util.ArrayList;

public class ScreeningsFragment extends Fragment {

    private FragmentScreeningsBinding binding;
    private SelectedMovieView selectedMovieView;
    private LiveBookingView liveBookingView;
    private GlobalDataView globalDataView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentScreeningsBinding.inflate(inflater, container, false);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        liveBookingView = new ViewModelProvider(requireActivity()).get(LiveBookingView.class);
        globalDataView = new ViewModelProvider(requireActivity()).get(GlobalDataView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        globalDataView.setFragmentName("Screenings");
        binding.textTitleScreenings.setText(selectedMovieView.getMovie().getValue().getTitle());
        binding.textPriceAdult.setText(AdultTicket.getFormattedPrice(AdultTicket.getPrice()));
        binding.textPriceChild.setText(ChildTicket.getFormattedPrice(ChildTicket.getPrice()));

        liveBookingView.getTotalFormatted().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String price) {

                binding.textTotal.setText(price);
            }
        });

        selectedMovieView.getItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<Screening>>() {
            @Override
            public void onChanged(ArrayList<Screening> screenings) {

                if (screenings.size() == 0) {
                    return;
                }
                ScreeningAdapter screeningAdapter = new ScreeningAdapter(getContext(), screenings);
                binding.selectScreening.setAdapter(screeningAdapter);

            }
        });

        binding.selectScreening.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Screening screening = (Screening) parent.getItemAtPosition(position);
                liveBookingView.setScreening(screening);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.inputNumAdults.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    liveBookingView.setNumAdultTickets(0);
                } else {
                    Integer val = Integer.parseInt(s.toString());
                    liveBookingView.setNumAdultTickets(val);
                }
            }
        });

        binding.inputNumChildren.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    liveBookingView.setNumChildTickets(0);
                } else {
                    Integer val = Integer.parseInt(s.toString());
                    liveBookingView.setNumChildTickets(val);
                }
            }
        });


        binding.screeningConfirm.setOnClickListener(v -> {
            if (globalDataView.getUser().getValue() == null) {
                Activity mainActivity = getActivity();
                if (mainActivity instanceof MainActivity) {
                    ((MainActivity) mainActivity).openLoginActivity();
                }
            }

                    NavHostFragment.findNavController(this).navigate(R.id.action_screeningsFragment_to_checkoutFragment);
                }

        );
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}