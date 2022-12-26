package com.example.BigScreenCinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.BigScreenCinema.ViewModels.Movie;
import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentMoviesBinding;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    private FragmentMoviesBinding binding;
    private MovieView movieModel;
    private ArrayList<Movie> movies;
    private SelectedMovieView selectedMovieView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        movieModel = new ViewModelProvider(this).get(MovieView.class);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        System.out.println(selectedMovieView.getSelectedMovie());
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.recyclerViewMovies;
        movies = movieModel.getItems();
        System.out.println("got movies");
        System.out.println(movies);
        System.out.println(recyclerView);

        recyclerView.setAdapter(new MovieAdapter(movies, NavHostFragment.findNavController(MoviesFragment.this), selectedMovieView));
        System.out.println("set adaptor");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}