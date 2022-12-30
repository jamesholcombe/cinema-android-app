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

import com.example.BigScreenCinema.Adapters.FeaturedMovieAdapter;
import com.example.BigScreenCinema.R;
import com.example.BigScreenCinema.ViewModels.DataModels.Movie;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static ArrayList<Movie> movies;
    private FragmentHomeBinding binding;
    private MovieView movieModel;
    private GlobalDataView globalDataModel;
    private SelectedMovieView selectedMovieView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        movieModel = new ViewModelProvider(this).get(MovieView.class);
        globalDataModel = new ViewModelProvider(requireActivity()).get(GlobalDataView.class);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        globalDataModel.setFragmentName("BigScreen Cinema");

        globalDataModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null) {

                binding.buttonBookings.setVisibility(View.GONE);
            } else {
                binding.buttonBookings.setVisibility(View.VISIBLE);
            }
        });

        movieModel.getItems().observe(getViewLifecycleOwner(), movies -> {
            NavController navController = NavHostFragment.findNavController(HomeFragment.this);

            RecyclerView recyclerView = binding.recyclerViewFeaturedMovies;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(new FeaturedMovieAdapter(movies, navController, selectedMovieView));
        });

        binding.buttonBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_FirstFragment_to_bookingsFragment);

            }
        });


        binding.buttonMoreMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_HomeFragment_to_MoviesFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}