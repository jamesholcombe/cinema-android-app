package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;

import com.example.BigScreenCinema.ViewModels.DataModels.Movie;
import com.example.BigScreenCinema.ViewModels.DataModels.Screening;
import com.google.firebase.firestore.CollectionReference;

public class SelectedMovieView extends BaseView<Screening> {

    MutableLiveData<Movie> movie = new MutableLiveData<>();

    public SelectedMovieView() {
        super("movies", Screening.class);
    }

    public MutableLiveData<Movie> getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie.setValue(movie);
        loadItems();
    }

    @Override
    protected CollectionReference getReference() {
        return db.collection(collectionName).document(movie.getValue().getId()).collection("screenings");
    }
}
