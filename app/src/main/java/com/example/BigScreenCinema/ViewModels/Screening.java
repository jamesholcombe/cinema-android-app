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
    private Screen screen;

    public Screening (Movie movie, Screen screen, DateTime dateTime, String id){
        super(id);
        this.movie = movie;
        this.datetime = dateTime;
        this.screen = screen;
    }

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
