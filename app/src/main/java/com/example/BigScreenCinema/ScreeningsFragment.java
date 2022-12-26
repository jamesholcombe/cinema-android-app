package com.example.BigScreenCinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentScreeningsBinding;

public class ScreeningsFragment extends Fragment {

    private FragmentScreeningsBinding binding;
    private SelectedMovieView selectedMovieView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentScreeningsBinding.inflate(inflater, container, false);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textTitleScreenings.setText(selectedMovieView.getSelectedMovie().getValue().getTitle());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}