package com.example.BigScreenCinema.ViewModels.DataModels;

import com.google.firebase.Timestamp;


enum Screen {
    A, B, C, D, E, F
}

public class Screening extends Base {

    private Screen screen;
    private Timestamp dateTime;
    private Movie movie;


    public Screening(Screen screen, String id, Timestamp dateTime) {
        super(id);
        this.dateTime = dateTime;
        this.screen = screen;

    }

    public Screening() {
        super();
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDateString() {
        return dateTime.toDate().toString();
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp datetime) {
        this.dateTime = datetime;
    }
}
