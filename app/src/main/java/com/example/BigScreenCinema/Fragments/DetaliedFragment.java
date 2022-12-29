package com.example.BigScreenCinema.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.BigScreenCinema.Utils.DownloadImageFromUri;
import com.example.BigScreenCinema.R;
import com.example.BigScreenCinema.ViewModels.DataModels.Movie;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentMovieDetailedBinding;

import java.util.ArrayList;
import java.util.Objects;

public class DetaliedFragment extends Fragment {

    private FragmentMovieDetailedBinding binding;

    private ArrayList<Movie> movies;
    private SelectedMovieView selectedMovieModel;
    private GlobalDataView globalDataView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMovieDetailedBinding.inflate(inflater, container, false);
        selectedMovieModel = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        globalDataView = new ViewModelProvider(requireActivity()).get(GlobalDataView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Movie movie = selectedMovieModel.getMovie().getValue();
        globalDataView.setFragmentName(Objects.requireNonNull(movie).getTitle());
        binding.ratingBarDetailed.setRating(movie.getRating());
        binding.textDescription.setText(movie.getDescriptionLong());


        binding.buttonDetailedToScreenings.setOnClickListener(v -> {

            NavHostFragment.findNavController(DetaliedFragment.this).navigate(R.id.action_detaliedFragment_to_screeningsFragment);
        });


        DownloadImageFromUri downloadImageFromUri = new DownloadImageFromUri(binding.imageViewMovieDetailed);
        downloadImageFromUri.execute(movie.getImageUri());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}