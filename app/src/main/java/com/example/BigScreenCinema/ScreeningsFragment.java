package com.example.BigScreenCinema;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.BigScreenCinema.ViewModels.LiveBookingView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentScreeningsBinding;

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
        binding.textTitleScreenings.setText(selectedMovieView.getSelectedMovie().getValue().getTitle());


        liveBookingView.getTotalFormatted().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String price) {

                binding.textTotal.setText(price);
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
    }







    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}