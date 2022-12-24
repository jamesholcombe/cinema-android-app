package com.example.BigScreenCinema.Models;

import com.google.type.DateTime;

import java.util.Date;

enum Screen {
    A,
    B,
    C,
    D
}


public class Screening {
    private DateTime datetime;
    private Movie movie;
    private Screen screen;


    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
