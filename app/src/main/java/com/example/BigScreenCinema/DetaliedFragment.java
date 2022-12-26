package com.example.BigScreenCinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.BigScreenCinema.ViewModels.Movie;
import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentMovieDetailedBinding;

import java.util.ArrayList;

public class DetaliedFragment extends Fragment {

    private FragmentMovieDetailedBinding binding;
    private MovieView movieModel;
    private ArrayList<Movie> movies;
    private SelectedMovieView selectedMovieModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMovieDetailedBinding.inflate(inflater, container, false);
        movieModel = new ViewModelProvider(this).get(MovieView.class);
        selectedMovieModel = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Movie movie = selectedMovieModel.getSelectedMovie().getValue();
        binding.textViewDetailed.setText(movie.getTitle());
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