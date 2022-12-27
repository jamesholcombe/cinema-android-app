package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;

import java.util.Objects;

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

    private CollectionReference getReference() {
        return db.collection(collectionName).document(Objects.requireNonNull(movie.getValue()).getTitle()).collection("screenings");
    }
}
