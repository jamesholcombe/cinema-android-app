package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SelectedMovieView extends ViewModel {
    private MutableLiveData<Movie> selectedMovie = new MutableLiveData<Movie>();

    public void selectMovie (Movie movie) {
        selectedMovie.setValue(movie);
    }

    public LiveData<Movie> getSelectedMovie () {
        return selectedMovie;
    }
}
