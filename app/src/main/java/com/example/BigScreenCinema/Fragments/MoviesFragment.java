package com.example.BigScreenCinema.Fragments;

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

import com.example.BigScreenCinema.Adapters.MovieAdapter;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentMoviesBinding;

public class MoviesFragment extends Fragment {

    private FragmentMoviesBinding binding;
    private MovieView movieModel;
    private GlobalDataView globalDataModel;
    private SelectedMovieView selectedMovieView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        movieModel = new ViewModelProvider(this).get(MovieView.class);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        globalDataModel = new ViewModelProvider(requireActivity()).get(GlobalDataView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        globalDataModel.setFragmentName("Movies");

        RecyclerView recyclerView = binding.recyclerViewMovies;
        NavController navController = NavHostFragment.findNavController(MoviesFragment.this);
        movieModel.getItems().observe(getViewLifecycleOwner(), movies -> {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new MovieAdapter(movies, navController, selectedMovieView));
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}