package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SelectedMovieView {
    private MutableLiveData<Movie> selectedMovie;
    public void selectMovie (Movie movie) {
        selectedMovie.setValue(movie);
    }

    public LiveData<Movie> getSelectedMovie () {
        return selectedMovie;
    }
}
