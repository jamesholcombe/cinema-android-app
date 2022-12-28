package com.example.BigScreenCinema;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.BigScreenCinema.ViewModels.LiveBookingView;
import com.example.BigScreenCinema.ViewModels.DataModels.Screening;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentScreeningsBinding;

import java.util.ArrayList;

public class ScreeningsFragment extends Fragment {

    private FragmentScreeningsBinding binding;
    private SelectedMovieView selectedMovieView;
    private LiveBookingView liveBookingView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentScreeningsBinding.inflate(inflater, container, false);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        liveBookingView = new ViewModelProvider(requireActivity()).get(LiveBookingView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textTitleScreenings.setText(selectedMovieView.getMovie().getValue().getTitle());


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
                ScreeningAdaptor screeningAdaptor = new ScreeningAdaptor(getContext(), screenings);
                binding.selectScreening.setAdapter(screeningAdaptor);

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


        binding.inputNumAdults.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Integer val = Integer.parseInt(textView.getText().toString());
                liveBookingView.setNumAdultTickets(val);
                System.out.println("adult ran");
                return true;
            }
        });
        binding.inputNumChildren.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Integer val = Integer.parseInt(textView.getText().toString());
                System.out.println("child ran");
                liveBookingView.setNumChildTickets(val);
                return true;
            }
        });

        binding.screeningConfirm.setOnClickListener(v -> {
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