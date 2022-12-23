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

import com.example.BigScreenCinema.Models.MovieModel;
import com.example.BigScreenCinema.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static ArrayList<Movie> movies;
    private MovieModel movieModel;




    public static void setMovies(ArrayList<Movie> movies) {
        movies = movies;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        movieModel = new ViewModelProvider(this).get(MovieModel.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        System.out.println("getting movies");

        movies = movieModel.getItems();
        System.out.println("got movies");
        System.out.println(movies);

        RecyclerView recyclerView = binding.recyclerViewFeaturedMovies;
        recyclerView.setAdapter(new FeaturedMovieAdapter(movies));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));



        binding.buttonMoreMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_HomeFragment_to_MoviesFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}