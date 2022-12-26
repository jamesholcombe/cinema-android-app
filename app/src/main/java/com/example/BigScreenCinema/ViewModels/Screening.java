package com.example.BigScreenCinema.ViewModels;

import com.google.type.DateTime;

enum Screen {
    A,
    B,
    C,
    D
}

public class Screening extends Base {
    private DateTime datetime;
    private Movie movie;
    private String movieId;
    private Screen screen;

    public Screening(Movie movie, Screen screen, DateTime dateTime, String id) {
        super(id);
        this.movie = movie;
        this.datetime = dateTime;
        this.screen = screen;
        this.movieId = movie.getId();
    }

    public Screening(String movieId, Screen screen, DateTime dateTime, String id) {
        super(id);
        this.movieId = movieId;
        this.screen = screen;
        this.datetime = dateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }
}
