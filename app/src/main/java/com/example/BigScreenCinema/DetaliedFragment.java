package com.example.BigScreenCinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.BigScreenCinema.ViewModels.Movie;
import com.example.BigScreenCinema.ViewModels.MovieView;
import com.example.BigScreenCinema.databinding.FragmentMoviesBinding;

import java.util.ArrayList;

public class DetaliedFragment extends Fragment {

    private FragmentMoviesBinding binding;
    private MovieView movieModel;
    private ArrayList<Movie> movies;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        movieModel = new ViewModelProvider(this).get(MovieView.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.recyclerViewMovies;
        movies = movieModel.getItems();
        System.out.println("got movies");
        System.out.println(movies);
        System.out.println(recyclerView);

        recyclerView.setAdapter(new MovieAdapter(movies));
        System.out.println("set adaptor");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}